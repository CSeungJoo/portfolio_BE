package kr.cseungjoo.commonmodule.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kr.cseungjoo.commonmodule.security.auth.PrincipalDetails;
import kr.cseungjoo.commonmodule.security.auth.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private static final String TOKEN_TYPE_KEY = "type";
    private static final String ACCESS_TOKEN_TYPE = "access";
    private static final String REFRESH_TOKEN_TYPE = "refresh";

    private final String secret;
    private final long tokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private Key key;
    private final UserDetailsService userDetailsService;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration_time}") long tokenValidityInSeconds,
            @Value("${jwt.refresh_expiration_time}") long refreshTokenValidityInSeconds,
            UserDetailsService userDetailsService) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInSeconds * 1000;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(Authentication authentication) {
        return createToken(authentication, ACCESS_TOKEN_TYPE, tokenValidityInMilliseconds);
    }

    public String createRefreshToken(Authentication authentication) {
        return createToken(authentication, REFRESH_TOKEN_TYPE, refreshTokenValidityInMilliseconds);
    }

    private String createToken(Authentication authentication, String tokenType, long validityInMilliseconds) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(TOKEN_TYPE_KEY, tokenType)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        PrincipalDetails principalDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(principalDetails, "", principalDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public boolean isRefreshToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return REFRESH_TOKEN_TYPE.equals(claims.get(TOKEN_TYPE_KEY));
    }

    public boolean isAccessToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return ACCESS_TOKEN_TYPE.equals(claims.get(TOKEN_TYPE_KEY));
    }
    public String refreshAccessToken(String refreshToken) {
        if (validateToken(refreshToken) && isRefreshToken(refreshToken)) {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            User principal = new User(claims.getSubject(), "", new ArrayList<>());

            Authentication authentication = new UsernamePasswordAuthenticationToken(principal, refreshToken, new ArrayList<>());
            return createAccessToken(authentication);
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }
}

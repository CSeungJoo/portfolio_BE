package kr.cseungjoo.commonmodule.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REFRESH_TOKEN_HEADER = "Refresh-Token";
    private final JwtProvider jwtProvider;

    // 실제 필터릴 로직
    // 토큰의 인증정보를 SecurityContext에 저장하는 역할 수행
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String jwt = resolveToken(httpServletRequest, AUTHORIZATION_HEADER);
        String refreshToken = resolveToken(httpServletRequest, REFRESH_TOKEN_HEADER);
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
            // 유효한 액세스 토큰이 있는 경우
            Authentication authentication = jwtProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else if (StringUtils.hasText(refreshToken) && jwtProvider.validateToken(refreshToken) && jwtProvider.isRefreshToken(refreshToken)) {
            // 액세스 토큰이 만료되었고, 유효한 리프레시 토큰이 있는 경우
            String newAccessToken = jwtProvider.refreshAccessToken(refreshToken);
            httpServletResponse.setHeader(AUTHORIZATION_HEADER, "Bearer " + newAccessToken);

            Authentication authentication = jwtProvider.getAuthentication(newAccessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("리프레시 토큰을 이용해 새 액세스 토큰을 생성하고 Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    // Request Header 에서 토큰 정보를 꺼내오기 위한 메소드
    private String resolveToken(HttpServletRequest request, String headerName) {
        String bearerToken = request.getHeader(headerName);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

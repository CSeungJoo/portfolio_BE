package kr.cseungjoo.authserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.cseungjoo.authserver.domain.RefreshToken;
import kr.cseungjoo.authserver.dto.LoginDto;
import kr.cseungjoo.authserver.dto.TokenDto;
import kr.cseungjoo.authserver.dto.UserDto;
import kr.cseungjoo.authserver.exception.RefreshTokenNotFoundException;
import kr.cseungjoo.authserver.exception.RefreshTokenNotValidException;
import kr.cseungjoo.authserver.feign.AuthFeignClient;
import kr.cseungjoo.commonmodule.Role;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.exception.ErrorCode;
import kr.cseungjoo.commonmodule.security.jwt.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final AuthFeignClient authFeignClient;
    private final RefreshTokenService refreshTokenService;

    public TokenDto login (LoginDto loginDto) {

        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> userEntity = authFeignClient.login(systemToken, loginDto);

        UserDto user = objectMapper.convertValue(userEntity.getBody().data(), UserDto.class);

        String accessToken = jwtProvider.generateAccessToken(user.getEmail(), Collections.singletonMap("role", user.getRole()));
        String refreshToken = jwtProvider.generateRefreshToken(user.getId(), user.getEmail());


        Optional<RefreshToken> optRt = refreshTokenService.findByUserId(user.getId());

        RefreshToken rt = optRt
                .map(srt -> {
                    srt.updateToken(accessToken, refreshToken);
                    return srt;
                }).orElseGet(() -> refreshTokenService.uploadRefreshToken(refreshToken, user.getId()));

        TokenDto tokenDto = TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return tokenDto;
    }

    public TokenDto refreshToken(String refreshTokenStr) {
        boolean isRefreshToken = jwtProvider.isRefreshToken(refreshTokenStr);

        if (!isRefreshToken) {
            throw new RefreshTokenNotValidException();
        }

        boolean exists = refreshTokenService.existsRefreshToken(refreshTokenStr);

        if (!exists) {
            throw new RefreshTokenNotFoundException(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
        }

        String email = jwtProvider.getEmailFromToken(refreshTokenStr);
        String role = jwtProvider.getRoleFromToken(refreshTokenStr);

        String accessToken = jwtProvider.generateAccessToken(email, Collections.singletonMap("role", role));

        TokenDto tokenDto = new TokenDto(accessToken, refreshTokenStr);

        return tokenDto;
    }
}

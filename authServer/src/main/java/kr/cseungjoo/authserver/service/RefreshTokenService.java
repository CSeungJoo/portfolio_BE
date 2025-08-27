package kr.cseungjoo.authserver.service;

import kr.cseungjoo.authserver.domain.RefreshToken;
import kr.cseungjoo.authserver.repository.RefreshTokenRepository;
import kr.cseungjoo.commonmodule.security.jwt.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public Optional<RefreshToken> findRefreshTokenByAccessToken(String accessToken) {
        return refreshTokenRepository.findByAccessToken(accessToken);
    }

    public Optional<RefreshToken> findByUserId(long userId) {
        return refreshTokenRepository.findById(userId);
    }

    public void removeUserRefreshToken(long userId) {
        refreshTokenRepository.deleteById(userId);
    }

    public void removeUserRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
    }

    public void removeRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }

    public RefreshToken uploadRefreshToken(String refreshToken, long userId) {
        return refreshTokenRepository.save(RefreshToken.builder()
                .id(userId)
                .refreshToken(refreshToken)
                .build());
    }

    public boolean existsRefreshToken(String refreshTokenStr) {
        boolean exists = refreshTokenRepository.existsByRefreshToken(refreshTokenStr);

        return exists;
    }
}

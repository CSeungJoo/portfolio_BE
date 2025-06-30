package kr.cseungjoo.authserver.repository;

import kr.cseungjoo.authserver.domain.RefreshToken;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends KeyValueRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByAccessToken(String accessToken);
    void deleteByRefreshToken(String refreshToken);
}

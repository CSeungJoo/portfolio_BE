package kr.cseungjoo.authserver.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash(value = "jwtToken", timeToLive = 60 * 60 * 24 * 13)
@AllArgsConstructor
public class RefreshToken {

    @Id
    private Long id;

    @Indexed
    private String refreshToken;


    public void updateToken(String accessToken, String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

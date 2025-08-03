package kr.cseungjoo.commonmodule.config;

import feign.codec.ErrorDecoder;
import kr.cseungjoo.commonmodule.exception.decoder.FeignExceptionDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignExceptionDecoder();
    }
}

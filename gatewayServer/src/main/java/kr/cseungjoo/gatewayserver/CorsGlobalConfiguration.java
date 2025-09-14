package kr.cseungjoo.gatewayserver;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.util.Arrays;

@Configuration
public class CorsGlobalConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);  // 쿠키/인증 포함 요청 허용
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://home.cseungjoo.kr:80", "http://127.0.0.1:5500")); // 프론트 도메인
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Refresh-Token"));  // 모든 헤더 허용
        config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS")); // 메서드 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 모든 경로에 적용

        return new CorsWebFilter(source);
    }

}

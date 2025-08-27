package kr.cseungjoo.awardserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "kr.cseungjoo.awardserver",
        "kr.cseungjoo.commonmodule"
})
public class AwardServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwardServerApplication.class, args);
    }

}

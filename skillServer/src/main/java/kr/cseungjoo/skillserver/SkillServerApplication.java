package kr.cseungjoo.skillserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "kr.cseungjoo.skillserver",
        "kr.cseungjoo.commonmodule"
})
public class SkillServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkillServerApplication.class, args);
    }

}

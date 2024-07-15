package kr.cseungjoo.portfolioeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PortfolioEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioEurekaServerApplication.class, args);
    }

}

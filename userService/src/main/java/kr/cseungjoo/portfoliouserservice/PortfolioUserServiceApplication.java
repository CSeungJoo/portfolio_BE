package kr.cseungjoo.portfoliouserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"kr.cseungjoo.portfoliouserservice", "kr.cseungjoo.commonmodule"})
@EnableDiscoveryClient
public class PortfolioUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioUserServiceApplication.class, args);
    }

}

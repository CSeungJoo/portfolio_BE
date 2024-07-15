package kr.cseungjoo.portfolioConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class PortfolioConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioConfigServerApplication.class, args);
	}

}

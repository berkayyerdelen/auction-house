package com.acme.auctionhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ScheduledProperties.class)
public class AuctionHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionHouseApplication.class, args);
	}

}

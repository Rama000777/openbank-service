package com.local.openbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TechnightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnightsApplication.class, args);
	}

}

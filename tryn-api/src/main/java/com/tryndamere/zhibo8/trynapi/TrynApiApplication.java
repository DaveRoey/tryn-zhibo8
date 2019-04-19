package com.tryndamere.zhibo8.trynapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TrynApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrynApiApplication.class, args);
	}

}


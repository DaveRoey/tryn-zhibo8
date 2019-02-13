package com.tryndamere.zhibo8.trynpersistent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication



public class TrynPersistentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrynPersistentApplication.class, args);
	}

}


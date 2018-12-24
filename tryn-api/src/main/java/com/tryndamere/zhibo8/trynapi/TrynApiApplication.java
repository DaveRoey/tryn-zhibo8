package com.tryndamere.zhibo8.trynapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tryndamere.zhibo8.trynpersistent.mapper")

public class TrynApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrynApiApplication.class, args);
	}

}


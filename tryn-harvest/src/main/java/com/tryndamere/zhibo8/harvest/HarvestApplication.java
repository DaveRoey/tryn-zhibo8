package com.tryndamere.zhibo8.harvest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.tryndamere.zhibo8.trynpersistent.mapper")
@ComponentScan("com.tryndamere.zhibo8")
public class HarvestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarvestApplication.class, args);
	}

}


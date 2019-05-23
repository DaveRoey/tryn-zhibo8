package com.tryndamere.zhibo8.harvest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class HarvestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarvestApplication.class, args);
    }

}


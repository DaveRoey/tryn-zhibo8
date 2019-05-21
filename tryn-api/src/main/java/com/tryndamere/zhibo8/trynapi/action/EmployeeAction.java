package com.tryndamere.zhibo8.trynapi.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
@RestController
@Configuration
@RequestMapping(value = "/api/*")
public class EmployeeAction extends BaseAction {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("hello")
    public String hello() {
         restTemplate.put("http://127.0.0.1:8082/api/put","a");
        return "aa";
    }
    @PutMapping("put")
    public String put(String a) {
        return "ok";
    }

}

package com.dev_ajay.scalerdemo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

//This AppConfig class will be responsible for creating and managing the RestTemplate bean,
// making it available for dependency injection throughout your application.
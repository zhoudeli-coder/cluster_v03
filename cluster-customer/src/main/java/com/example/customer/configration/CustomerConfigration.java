package com.example.customer.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfigration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

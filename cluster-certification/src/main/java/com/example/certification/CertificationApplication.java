package com.example.certification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example")
@EntityScan("com.example.common.entity")
public class CertificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(CertificationApplication.class, args);
    }
}

package com.company.sikayet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SikayetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SikayetApplication.class, args);
    }
}

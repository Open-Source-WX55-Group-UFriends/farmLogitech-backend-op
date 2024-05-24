package com.farmlogitech.farmlogitechbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FarmLogitechBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmLogitechBackendApplication.class, args);
    }

}

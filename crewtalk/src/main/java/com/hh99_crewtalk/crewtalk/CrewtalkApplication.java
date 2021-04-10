package com.hh99_crewtalk.crewtalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CrewtalkApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrewtalkApplication.class, args);
    }

}

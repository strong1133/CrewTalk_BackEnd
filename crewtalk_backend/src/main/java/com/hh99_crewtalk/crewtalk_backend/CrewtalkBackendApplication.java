package com.hh99_crewtalk.crewtalk_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CrewtalkBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrewtalkBackendApplication.class, args);
    }

}

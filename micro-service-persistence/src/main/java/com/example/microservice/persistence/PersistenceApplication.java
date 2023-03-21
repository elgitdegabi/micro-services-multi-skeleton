package com.example.microservice.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Persistence application spring boot main class
 */
@EnableKafka
@SpringBootApplication
public class PersistenceApplication {

    /**
     * Spring boot main
     *
     * @param args {@link String[]} of arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PersistenceApplication.class, args);
    }
}

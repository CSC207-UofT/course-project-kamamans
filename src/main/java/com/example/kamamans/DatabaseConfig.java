package com.example.kamamans;

import entities.Airport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner commandLineRunner(DatabaseRepository repository) {
        return args -> {
//            Airport toronto = new Airport("Toronto", "6ix");
//            Airport vancouver = new Airport("Vancouver", "lacroix");
//
//            repository.save(toronto);
//            repository.save(vancouver);
        };
    }
}
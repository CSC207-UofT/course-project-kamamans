package com.example.kamamans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("entities")
public class KamamansApplication {

	public static void main(String[] args) {
		SpringApplication.run(KamamansApplication.class, args);
	}


}

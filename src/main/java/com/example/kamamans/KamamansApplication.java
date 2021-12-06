package com.example.kamamans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Class a part of framework and drivers
 */

@SpringBootApplication
@EntityScan("entities")
public class KamamansApplication {

	public static void main(String[] args) {
		SpringApplication.run(KamamansApplication.class, args);
	}


}

package com.example.kamamans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KamamansApplication {

	public static void main(String[] args) {
		SpringApplication.run(KamamansApplication.class, args);
	}

	@GetMapping
	public String[] hello() {
		// return "hello world!";
		String[] output = new String[3];
		output[0] = "Hello";
		output[1] = "World!";
		output[2] = "Hello Friends!";
		return output;
	}
}

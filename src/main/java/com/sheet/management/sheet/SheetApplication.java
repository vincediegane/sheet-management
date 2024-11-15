package com.sheet.management.sheet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SheetApplication.class, args);
	}

	@Bean
	CommandLineRunner run() {
		return args -> {

		};
	}

}

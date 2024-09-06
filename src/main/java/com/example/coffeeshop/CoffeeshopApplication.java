package com.example.coffeeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CoffeeshopApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoffeeshopApplication.class, args);
	}

}

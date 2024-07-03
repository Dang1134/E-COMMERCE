package com.example.uniclub05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Uniclub05Application {

	public static void main(String[] args) {
		SpringApplication.run(Uniclub05Application.class, args);
	}

}

package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Notification Application of Intellect design
 * 
 * @author Siva
 * 
 */
@OpenAPIDefinition(info = @Info(title = "Intellect", version = "2.0", description = "Notification API Details Information"))
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

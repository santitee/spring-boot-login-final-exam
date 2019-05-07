package com.codecamp3.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan(basePackages = "com.codecamp3.boot")
public class FinalexamApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalexamApplication.class, args);
	}

}

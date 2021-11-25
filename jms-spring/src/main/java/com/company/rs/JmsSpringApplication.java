package com.company.rs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JmsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsSpringApplication.class, args);
	}

}

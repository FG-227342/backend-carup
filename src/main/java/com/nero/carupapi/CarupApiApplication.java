package com.nero.carupapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CarupApiApplication {

	public static void main(String[] args) {
		ApplicationContext c = SpringApplication.run(CarupApiApplication.class, args);
	}

}

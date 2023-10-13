package com.more.more50;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class More50Application {

	public static void main(String[] args) {
		SpringApplication.run(More50Application.class, args);
	}

}

package com.hongquan.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hongquan"})
public class HelloworldApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}

}

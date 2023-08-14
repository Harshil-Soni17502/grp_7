package com.bank.OnlinebankingSystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OnlinebankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinebankingSystemApplication.class, args);
	}
	
	@GetMapping
	public String hello() {
		return "Test success";
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}

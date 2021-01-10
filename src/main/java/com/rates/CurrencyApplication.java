package com.rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.annotation.JsonFormat;

@SpringBootApplication
@EnableScheduling
public class CurrencyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}
}

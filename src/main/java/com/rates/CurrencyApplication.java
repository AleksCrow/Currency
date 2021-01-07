package com.rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonFormat;

@SpringBootApplication
public class CurrencyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}
}

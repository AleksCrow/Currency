package com.rates;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.rates.average.controller.AverageRateController;

@SpringBootTest
class CurrencyApplicationTests {

	@Autowired
	private AverageRateController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}

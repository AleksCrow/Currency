package com.rates;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.rates.controller.AverageRateController;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyApplicationTests {

	@Autowired
	private AverageRateController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}

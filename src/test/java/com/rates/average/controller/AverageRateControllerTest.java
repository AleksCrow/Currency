package com.rates.average.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.rates.average.model.AverageRate;
import com.rates.average.service.AverageRateService;

@WebMvcTest(AverageRateController.class)
class AverageRateControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AverageRateService service;
	
	@Test
	void shouldReturnEmpty() throws Exception {
		this.mockMvc.perform(get("/average/rates"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string("[]"));
	}
	
	@Test
	void shouldReturnRates() throws Exception {
		AverageRate rate1 = new AverageRate("USD", "UAH", LocalDateTime.of(2021, 01, 05, 16, 30, 0), 28.15F, 28.45F);
		
        when(service.getRates()).thenReturn(List.of(rate1));
		
		this.mockMvc.perform(get("/average/rates"))
					.andDo(print())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().string("[{\"sourceCurrency\":\"USD\",\"targetCurrency\":\"UAH\",\"rateBuy\":28.15,\"rateSell\":28.45}]"));
	}
	
	@Test
	void shouldLoadProvidersData() throws Exception {
		this.mockMvc.perform(get("/average/update"))
					.andDo(print())
					.andExpect(status().isNoContent());
	}
	
	@Test
	void shouldReturnRatesBetweenActualDates() throws Exception {
		AverageRate rate1 = new AverageRate("USD", "UAH", LocalDateTime.of(2021, 01, 03, 18, 0, 0), 28.15F, 28.45F);
		AverageRate rate2 = new AverageRate("EUR", "UAH", LocalDateTime.of(2021, 01, 03, 18, 0, 0), 34.65F, 35.05F);
		AverageRate rate3 = new AverageRate("RUB", "UAH", LocalDateTime.of(2021, 01, 03, 18, 0, 0), .36F, .4F);
		
		when(service.getAverageBetween(LocalDateTime.of(2021, 01, 03, 15, 00, 00), LocalDateTime.of(2021, 01, 03, 20, 00, 00))).thenReturn(List.of(rate1, rate2, rate3));
		
		String result = "[{\"sourceCurrency\":\"USD\",\"targetCurrency\":\"UAH\",\"rateBuy\":28.15,\"rateSell\":28.45},"
				+ "{\"sourceCurrency\":\"EUR\",\"targetCurrency\":\"UAH\",\"rateBuy\":34.65,\"rateSell\":35.05},"
				+ "{\"sourceCurrency\":\"RUB\",\"targetCurrency\":\"UAH\",\"rateBuy\":0.36,\"rateSell\":0.4}]";
		
		this.mockMvc.perform(get("/average/filter?startTime=2021-01-03T15:00:00&endTime=2021-01-03T20:00:00"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(result));
	}
	
	@Test
	void shouldReturnEmptyRatesBetweenWrongDates() throws Exception {
		when(service.getAverageBetween(LocalDateTime.of(2021, 01, 03, 10, 00, 00), LocalDateTime.of(2021, 01, 03, 15, 00, 00))).thenReturn(List.of());
		
		this.mockMvc.perform(get("/average/filter?startTime=2021-01-03T10:00:00&endTime=2021-01-03T15:00:00"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string("[]"));
	}
}

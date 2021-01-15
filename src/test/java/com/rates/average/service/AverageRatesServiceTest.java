package com.rates.average.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rates.average.model.AverageRate;
import com.rates.average.service.AverageRateService;
import com.rates.providers.model.Monobank;
import com.rates.providers.model.Nationalbank;
import com.rates.providers.model.Privatbank;
import com.rates.providers.service.MonobankService;
import com.rates.providers.service.NationalbankService;
import com.rates.providers.service.PrivatbankService;

@SpringBootTest
class AverageRatesServiceTest {
	
	@Autowired
	private AverageRateService service;
	
	@MockBean
	private MonobankService monobankService;
	
	@MockBean
	private PrivatbankService privatbankService;
	
	@MockBean
	private NationalbankService nationalbankService;
	
	@BeforeEach
	void setUp() {
		Monobank monobank1 = new Monobank(1l, 840, 980, LocalDateTime.of(2021, 01, 05, 16, 30, 0), 28.1F, 28.4F);
		Monobank monobank2 = new Monobank(2l, 978, 980, LocalDateTime.of(2021, 01, 05, 16, 0, 0), 34.8F, 35.1F);
		Monobank monobank3 = new Monobank(3l, 643, 980, LocalDateTime.of(2021, 01, 05, 16, 30, 0), .38F, .41F);
		Monobank monobank4 = new Monobank(3l, 840, 980, LocalDateTime.of(2021, 01, 03, 18, 00, 0), 28.1F, 28.4F);
		
		Privatbank privatbank1 = new Privatbank(1l, "USD", "UAH", LocalDateTime.of(2021, 01, 05, 16, 0, 0), "28.2", "28.5");
		Privatbank privatbank2 = new Privatbank(2l, "EUR", "UAH", LocalDateTime.of(2021, 01, 05, 16, 0, 0), "34.7", "35.0");
		Privatbank privatbank3 = new Privatbank(3l, "RUB", "UAH", LocalDateTime.of(2021, 01, 05, 16, 0, 0), "0.37", "0.4");
		Privatbank privatbank4 = new Privatbank(2l, "EUR", "UAH", LocalDateTime.of(2021, 01, 03, 18, 0, 0), "34.7", "35.0");
		
		Nationalbank nationalbank1 = new Nationalbank(1l, "USD", LocalDateTime.of(2021, 01, 03, 16, 0, 0), "28.2", "28.5");
		Nationalbank nationalbank2 = new Nationalbank(2l, "EUR", LocalDateTime.of(2021, 01, 03, 16, 0, 0), "34.6", "35.1");
		Nationalbank nationalbank3 = new Nationalbank(3l, "RUB", LocalDateTime.of(2021, 01, 03, 16, 0, 0), "0.36", "0.4");
		
		Mockito.when(monobankService.findAllByCurrency()).thenReturn(List.of(monobank1, monobank2, monobank3));
		Mockito.when(privatbankService.findAllByCurrency()).thenReturn(List.of(privatbank1, privatbank2, privatbank3));
		Mockito.when(nationalbankService.findAllByCurrency()).thenReturn(List.of());
		
		Mockito.when(monobankService.findBetween(LocalDateTime.of(2021, 01, 03, 15, 00, 0), LocalDateTime.of(2021, 01, 03, 20, 00, 0)))
								.thenReturn(List.of(monobank4));
		Mockito.when(privatbankService.findBetween(LocalDateTime.of(2021, 01, 03, 15, 00, 0), LocalDateTime.of(2021, 01, 03, 20, 00, 0)))
								.thenReturn(List.of(privatbank4));
		Mockito.when(nationalbankService.findBetween(LocalDateTime.of(2021, 01, 03, 15, 00, 0), LocalDateTime.of(2021, 01, 03, 20, 00, 0)))
								.thenReturn(List.of(nationalbank1, nationalbank2, nationalbank3));
	}
	
	@Test
	void shouldReturnAverageRates() {
		AverageRate rateUSD = new AverageRate("USD", "UAH", LocalDateTime.of(2021, 01, 05, 16, 30, 0), 28.15F, 28.45F);
		AverageRate rateEUR = new AverageRate("EUR", "UAH", LocalDateTime.of(2021, 01, 05, 16, 30, 0), 34.75F, 35.05F);
		AverageRate rateRUB = new AverageRate("RUB", "UAH", LocalDateTime.of(2021, 01, 05, 16, 30, 0), .375F, .405F);
		
		List<AverageRate> rates = service.getRates();
		
		assertThat(List.of(rateEUR, rateUSD, rateRUB)).isEqualTo(rates);
	}
	
	@Test
	void shouldReaturnAverageRatesBetweenDates() {
		AverageRate rateUSD = new AverageRate("USD", "UAH", LocalDateTime.of(2021, 01, 03, 18, 0, 0), 28.15F, 28.45F);
		AverageRate rateEUR = new AverageRate("EUR", "UAH", LocalDateTime.of(2021, 01, 03, 18, 0, 0), 34.65F, 35.05F);
		AverageRate rateRUB = new AverageRate("RUB", "UAH", LocalDateTime.of(2021, 01, 03, 18, 0, 0), .36F, .4F);
		
		List<AverageRate> rates = service.getAverageBetween(LocalDateTime.of(2021, 01, 03, 15, 00, 0), LocalDateTime.of(2021, 01, 03, 20, 00, 0));
		
		assertThat(List.of(rateEUR, rateUSD, rateRUB)).isEqualTo(rates);
	}
		
}

package com.rates.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rates.model.AverageRate;
import com.rates.repository.AverageRateRepository;
import com.rates.utils.AverageRateUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AverageRateService {
	
	private MonobankService monobankService;
	private PrivatbankService privatbankService;
	
	private AverageRateRepository repository;

	public AverageRateService(AverageRateRepository repository, MonobankService monobankService, PrivatbankService privatbankService) {
		this.repository = repository;
        this.monobankService = monobankService;
        this.privatbankService = privatbankService;
    }
	
	public List<AverageRate> getRates() {
		log.info("rates load");
		return repository.findTop3ByOrderByDateDesc();
	}
	
	public void loadRatesData() {
		loadData();
		log.info("Rates from api loaded");
		getAverage();
		log.info("Average rates ready");
	}
	
	public List<AverageRate> getAverageBetween(LocalDateTime startTime, LocalDateTime endTime) {
		if (endTime == null && startTime != null) {
			endTime = LocalDateTime.now();
		}
		if (endTime != null && startTime == null) {
			endTime = endTime.plusSeconds(1);
			startTime = LocalDateTime.of(1970, 1, 1, 0, 00, 00, 000);
		}
		
		log.info("startDate = {}", startTime);
		log.info("endDate = {}", endTime);
		
		List<AverageRate> averageRates = new ArrayList<>();
		
		monobankService.findBetween(startTime, endTime).forEach(r -> averageRates.add(AverageRateUtils.converter(r)));
		privatbankService.findBetween(startTime, endTime).forEach(r -> averageRates.add(AverageRateUtils.converter(r)));
		
		List<AverageRate> result = new ArrayList<>();
		
		Map<String, Double> mapBuy = getAverageBuy(averageRates);
		Map<String, Double> mapSell = getAverageSell(averageRates);
		
		mapBuy.entrySet().forEach(a -> result.add(
						new AverageRate(a.getKey(), 
							"UAH", 
							LocalDateTime.now(), 
							(float) (a.getValue() / 10000), 
							(float) (mapSell.get(a.getKey()) / 10000))));
		
		return result;
	}
	
	private void getAverage() {
		List<AverageRate> averageRates = new ArrayList<>();
		
		monobankService.findAllByCurrency().forEach(r -> averageRates.add(AverageRateUtils.converter(r)));
		privatbankService.findAllByCurrency().forEach(r -> averageRates.add(AverageRateUtils.converter(r)));
		
		Map<String, Double> mapBuy = getAverageBuy(averageRates);
		Map<String, Double> mapSell = getAverageSell(averageRates);
		
		mapBuy.entrySet().forEach(a -> repository.save(
						new AverageRate(a.getKey(), 
								"UAH", 
								LocalDateTime.now(), 
								(float) (a.getValue() / 10000), 
								(float) (mapSell.get(a.getKey()) / 10000))));
	}
	
	private void loadData() {
		monobankService.loadRatesData();
		privatbankService.loadRatesData();
	}
	
	private Map<String, Double> getAverageBuy(List<AverageRate> averageRates) {
		return averageRates.stream()
				.collect(Collectors.groupingBy(AverageRate::getSourceCurrency, 
						Collectors.averagingInt(r -> (int) (r.getRateBuy() * 10000))));
	}
	
	private Map<String, Double> getAverageSell(List<AverageRate> averageRates) {
		return averageRates.stream()
				.collect(Collectors.groupingBy(AverageRate::getSourceCurrency, 
						Collectors.averagingInt(r -> (int) (r.getRateSell() * 10000))));
	}
}

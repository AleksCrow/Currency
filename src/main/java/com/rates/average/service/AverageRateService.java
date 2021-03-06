package com.rates.average.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rates.average.model.AverageRate;
import com.rates.average.repository.AverageRateRepository;
import com.rates.providers.service.MonobankService;
import com.rates.providers.service.NationalbankService;
import com.rates.providers.service.PrivatbankService;
import com.rates.utils.ProviderToAverageRate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AverageRateService {
	
	private final MonobankService monobankService;
	private final PrivatbankService privatbankService;
	private final NationalbankService nationalbankService;
	
	private final AverageRateRepository repository;

	public AverageRateService(AverageRateRepository repository, 
						MonobankService monobankService, 
						PrivatbankService privatbankService,
						NationalbankService nationalbankService) {
		this.repository = repository;
        this.monobankService = monobankService;
        this.privatbankService = privatbankService;
        this.nationalbankService = nationalbankService;
    }
	
	public List<AverageRate> getRates() {
		getAverage();
		log.info("rates loaded");
		return repository.findTop3ByOrderByDateDesc();
	}
	
	public void loadRatesData() {
		loadData();
		log.info("Rates from api loaded");
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
		
		monobankService.findBetween(startTime, endTime).forEach(r -> averageRates.add(ProviderToAverageRate.converter(r)));
		privatbankService.findBetween(startTime, endTime).forEach(r -> averageRates.add(ProviderToAverageRate.converter(r)));
		nationalbankService.findBetween(startTime, endTime).forEach(r -> averageRates.add(ProviderToAverageRate.converter(r)));
		
		List<AverageRate> result = new ArrayList<>();
		
		Map<String, Integer> mapBuy = getAverageBuy(averageRates);
		Map<String, Integer> mapSell = getAverageSell(averageRates);
		
		LocalDateTime lastTime = averageRates.stream()
				.map(AverageRate::getDate)
				.max(LocalDateTime::compareTo).orElse(LocalDateTime.now());
		
		mapBuy.entrySet().forEach(a -> result.add(
						new AverageRate(a.getKey(), 
							"UAH", 
							lastTime, 
							((float) a.getValue() / 10000), 
							((float) mapSell.get(a.getKey()) / 10000))));
		
		log.warn("{}", result.toString());
		return result;
	}
	
	public void getAverage() {
		List<AverageRate> averageRates = new ArrayList<>();
		
		log.info("{}", privatbankService.findAllByCurrency());
		
		monobankService.findAllByCurrency().forEach(r -> averageRates.add(ProviderToAverageRate.converter(r)));
		privatbankService.findAllByCurrency().forEach(r -> averageRates.add(ProviderToAverageRate.converter(r)));
		nationalbankService.findAllByCurrency().forEach(r -> averageRates.add(ProviderToAverageRate.converter(r)));
		
		Map<String, Integer> mapBuy = getAverageBuy(averageRates);
		Map<String, Integer> mapSell = getAverageSell(averageRates);
		
		LocalDateTime lastTime = averageRates.stream()
									.map(AverageRate::getDate)
									.max(LocalDateTime::compareTo).orElse(LocalDateTime.now());
		
		mapBuy.entrySet().forEach(a -> repository.save(
						new AverageRate(a.getKey(), 
								"UAH", 
								lastTime, 
								((float) a.getValue()) / 10000, 
								((float) mapSell.get(a.getKey()) / 10000))));
		
		log.info("Average rates ready");
	}
	
	private void loadData() {
		monobankService.loadRatesData();
		privatbankService.loadRatesData();
		nationalbankService.loadRatesData();
	}
	
	private Map<String, Integer> getAverageBuy(List<AverageRate> averageRates) {
		Map<String, Integer> map = averageRates.stream()
				.collect(Collectors.groupingBy(AverageRate::getSourceCurrency, 
						Collectors.summingInt(r -> (int) (r.getRateBuy() * 10000))));
		
		return calculateAverage(map, averageRates);
	}
	
	private Map<String, Integer> getAverageSell(List<AverageRate> averageRates) {
		Map<String, Integer> map = averageRates.stream()
				.collect(Collectors.groupingBy(AverageRate::getSourceCurrency, 
						Collectors.summingInt(r -> (int) (r.getRateSell() * 10000))));
		
		return calculateAverage(map, averageRates);
	}
	
	private Map<String, Integer> calculateAverage(Map<String, Integer> map, List<AverageRate> averageRates) {
		int usdCount = (int) averageRates.stream().filter(r -> r.getSourceCurrency().equals("USD")).count();
		int rubCount = (int) averageRates.stream().filter(r -> r.getSourceCurrency().equals("RUB")).count();
		int eurCount = (int) averageRates.stream().filter(r -> r.getSourceCurrency().equals("EUR")).count();
		
		Map<String, Integer> result = new HashMap<>();
		if (usdCount != 0) {
			result.put("USD", map.get("USD") / usdCount);
		}
		if (rubCount != 0) {
			result.put("RUB", map.get("RUB") / rubCount);
		}
		if (eurCount != 0) {
			result.put("EUR", map.get("EUR") / eurCount);
		}
		
		return result;
	}
}

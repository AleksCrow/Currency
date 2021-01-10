package com.rates.providers.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.rates.providers.model.AbstractEntity;

public interface CommonController<T extends AbstractEntity> {
	
	@GetMapping
	List<T> getRates();
	
	@GetMapping
	void loadRatesData();
	
	@GetMapping
	List<T> findBetween(LocalDateTime starTime, LocalDateTime endDate);
}

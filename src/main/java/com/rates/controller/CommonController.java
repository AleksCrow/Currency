package com.rates.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.rates.model.AbstractEntity;

public interface CommonController<T extends AbstractEntity> {
	
	@GetMapping
	List<T> getRates();
	
	@GetMapping
	List<T> loadRatesData();
	
	@GetMapping
	void load();
}

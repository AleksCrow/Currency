package com.rates.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rates.model.AbstractEntity;
import com.rates.service.CommonService;

public abstract class AbstractController<T extends AbstractEntity, S extends CommonService<T>> implements CommonController<T> {

	private final S service;

	@Autowired
	protected AbstractController(S service) {
		this.service = service;
	}

	@Override
	public List<T> getRates() { 
		return service.findAllByCurrency();
	}

	@Override
	public void loadRatesData() {
		service.loadRatesData();
	}

	@Override
	public List<T> findBetween(LocalDateTime starTime, LocalDateTime endTime) {
		return service.findBetween(starTime, endTime);
	}
}

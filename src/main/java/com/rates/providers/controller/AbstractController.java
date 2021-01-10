package com.rates.providers.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.rates.providers.model.AbstractEntity;
import com.rates.providers.service.CommonService;

public abstract class AbstractController<T extends AbstractEntity, S extends CommonService<T>> implements CommonController<T> {

	private final S service;

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

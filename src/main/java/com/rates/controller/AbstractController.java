package com.rates.controller;

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
		return service.findAll();
	}

	@Override
	public List<T> loadRatesData() {
		return service.loadRatesData();
	}

	@Override
	public void load() {
		List<T> entityList = service.loadRatesData();
		service.save(entityList);
	}
	
	
}

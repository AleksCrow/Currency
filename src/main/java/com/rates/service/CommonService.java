package com.rates.service;

import java.util.List;

import com.rates.model.AbstractEntity;

public interface CommonService<T extends AbstractEntity> {
	
	List<T> findAll();
	
	List<T> loadRatesData();

	void save(List<T> entity);
}

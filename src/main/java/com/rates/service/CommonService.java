package com.rates.service;

import java.time.LocalDateTime;
import java.util.List;

import com.rates.model.AbstractEntity;

public interface CommonService<T extends AbstractEntity> {
	
	List<T> findAllByCurrency();
	
	void loadRatesData();
	
	List<T> findBetween(LocalDateTime startTime, LocalDateTime endTime);
}

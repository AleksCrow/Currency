package com.rates.providers.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.rates.providers.model.AbstractEntity;
import com.rates.providers.repository.CommonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractService<T extends AbstractEntity, R extends CommonRepository<T>> implements CommonService<T> {
	
	protected final R repository;

    @Autowired
    protected AbstractService(R repository) {
        this.repository = repository;
    }
    
    @Override
	public List<T> findAllByCurrency() {
    	List<T> rates = repository.findAllByCurrency();
    	if (rates.isEmpty()) {
			return rates;
		}
		return rates.stream().skip(rates.size() - 3).collect(Collectors.toList());
	}

	@Override
	public List<T> findBetween(LocalDateTime startTime, LocalDateTime endTime) {
		if (endTime == null && startTime != null) {
			endTime = LocalDateTime.now();
		}
		if (endTime != null && startTime == null) {
			endTime = endTime.plusSeconds(1);
			startTime = LocalDateTime.of(1970, 1, 1, 0, 00, 00);
		}
		log.info("startDate = {}", startTime);
		log.info("endDate = {}", endTime);
		return repository.findBetween(startTime, endTime);
	}
}

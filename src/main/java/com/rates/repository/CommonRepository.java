package com.rates.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.rates.model.AbstractEntity;

@NoRepositoryBean
public interface CommonRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {
	
	T save(T rates);
	
	List<T> findAllByCurrency();
	
	List<T> findBetween(LocalDateTime startTime, LocalDateTime endTime);
}
package com.rates.providers.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.rates.providers.model.AbstractEntity;

@NoRepositoryBean
@Transactional(readOnly = true)
public interface CommonRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {
	
	@Transactional
	T save(T rates);
	
	List<T> findAllByCurrency();
	
	List<T> findBetween(LocalDateTime startTime, LocalDateTime endTime);
}
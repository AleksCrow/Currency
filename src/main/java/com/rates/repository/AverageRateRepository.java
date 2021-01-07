package com.rates.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rates.model.AverageRate;

@Repository
@Transactional(readOnly = true)
public interface AverageRateRepository extends JpaRepository<AverageRate, Long> {

	@Override
    @Transactional
	AverageRate save(AverageRate rate);
	
	List<AverageRate> findTop3ByOrderByDateDesc();
}

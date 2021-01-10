package com.rates.providers.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rates.providers.model.Monobank;

@Repository
@Transactional(readOnly = true)
public interface MonobankRepository extends CommonRepository<Monobank> {

	@Query("SELECT r FROM Monobank r WHERE (r.sourceCurrency = 840 or r.sourceCurrency = 978 or r.sourceCurrency = 643)"
	      + " and r.targetCurrency = 980 ORDER BY r.date")
	List<Monobank> findAllByCurrency();
	
	@Query("SELECT r FROM Monobank r WHERE (r.date BETWEEN :startDate AND :endDate) AND ((r.sourceCurrency = 840 OR r.sourceCurrency = 978 OR r.sourceCurrency = 643)"
			+ " AND r.targetCurrency = 980) ORDER BY r.date")
    List<Monobank> findBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}

package com.rates.providers.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rates.providers.model.Monobank;
import com.rates.providers.model.Nationalbank;

@Repository
@Transactional(readOnly = true)
public interface NationalbankRepository extends CommonRepository<Nationalbank> {

	@Query("select r FROM Nationalbank r ORDER BY r.date")
	List<Nationalbank> findAllByCurrency();
	
	@Query("SELECT r FROM Nationalbank r WHERE r.date BETWEEN :startDate AND :endDate ORDER BY r.date")
    List<Nationalbank> findBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}

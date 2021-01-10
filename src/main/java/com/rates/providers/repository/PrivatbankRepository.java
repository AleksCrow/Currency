package com.rates.providers.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rates.providers.model.Privatbank;

@Repository
@Transactional(readOnly = true)
public interface PrivatbankRepository extends CommonRepository<Privatbank> {

	@Query("select r FROM Privatbank r WHERE r.sourceCurrency IN ('USD', 'EUR', 'RUR')"
		      + " AND r.targetCurrency IN ('UAH') ORDER BY r.date")
	List<Privatbank> findAllByCurrency();
	
	@Query("SELECT r FROM Privatbank r WHERE (r.date BETWEEN :startDate AND :endDate) AND (r.sourceCurrency IN ('USD', 'EUR', 'RUR')"
			+ " AND r.targetCurrency IN ('UAH')) ORDER BY r.date")
    List<Privatbank> findBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}

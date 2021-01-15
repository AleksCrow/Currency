package com.rates.average.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.rates.CurrencyApplication;
import com.rates.average.model.AverageRate;
import com.rates.average.repository.AverageRateRepository;

@DataJpaTest
class AverageRateRepositoryTest {
	
	  @Autowired 
	  private EntityManager entityManager;
	  
	  @Autowired 
	  private AverageRateRepository repository;

	  @Test
	  void injectedComponentsAreNotNull(){
		  assertThat(entityManager).isNotNull();
		  assertThat(repository).isNotNull();
	  }

	  @Test
	  void shouldReturnAverageRates() {
		  AverageRate rate1 = new AverageRate("USD", "UAH", LocalDateTime.of(2021, 01, 05, 16, 0, 0), 28.1F, 28.4F);
		  AverageRate rate2 = new AverageRate("EUR", "UAH", LocalDateTime.of(2021, 01, 05, 17, 0, 0), 34.8F, 35.1F);
		  AverageRate rate3 = new AverageRate("RUB", "UAH", LocalDateTime.of(2021, 01, 05, 16, 30, 0), .38F, .395F);
		  
		  entityManager.persist(rate1);
		  entityManager.persist(rate2);
		  entityManager.persist(rate3);
		  entityManager.flush();
		
		  List<AverageRate> found = repository.findTop3ByOrderByDateDesc();
		
		  assertThat(found).isEqualTo(List.of(rate2, rate3, rate1));
	  }
}

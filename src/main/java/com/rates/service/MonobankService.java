package com.rates.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rates.model.Monobank;
import com.rates.repository.MonobankRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MonobankService extends AbstractService<Monobank, MonobankRepository> {
	
	@Value("${api.monobank}")
	private String apiUrl;

    public MonobankService(MonobankRepository repository) {
        super(repository);
    }

	@Override
	public void loadRatesData() {
        RestTemplate restTemplate = new RestTemplate();
        List<Monobank> entity = Arrays.asList(restTemplate.getForEntity(apiUrl, Monobank[].class).getBody());
        List<Monobank> ratesListFromDb = repository.findAll();
		if (ratesListFromDb.stream().noneMatch(entity::contains)) {
			entity.forEach(repository::save);
		}
        log.info("Monobank rates loaded success");
	}
}

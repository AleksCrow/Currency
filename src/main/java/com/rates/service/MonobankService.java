package com.rates.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rates.model.Monobank;
import com.rates.repository.MonobankRepository;

@Service
public class MonobankService extends AbstractService<Monobank, MonobankRepository> {
	
	private final String API_URL = "https://api.monobank.ua/bank/currency";

    public MonobankService(MonobankRepository repository) {
        super(repository);
    }
	
	@Override
	public List<Monobank> loadRatesData() {
        RestTemplate restTemplate = new RestTemplate();
        Monobank[] entity = restTemplate.getForEntity(API_URL, Monobank[].class).getBody();
        return Arrays.asList(entity); 
	}
}

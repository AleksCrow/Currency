package com.rates.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rates.model.Privatbank;
import com.rates.repository.PrivatbankRepository;

@Service
public class PrivatbankService extends AbstractService<Privatbank, PrivatbankRepository> {
	
	private final String API_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    public PrivatbankService(PrivatbankRepository repository) {
        super(repository);
    }
	
	public List<Privatbank> loadRatesData() {
        RestTemplate restTemplate = new RestTemplate();
        Privatbank[] entity = restTemplate.getForEntity(API_URL, Privatbank[].class).getBody();
        return Arrays.asList(entity); 
	}
}

package com.rates.providers.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rates.providers.model.Privatbank;
import com.rates.providers.repository.PrivatbankRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrivatbankService extends AbstractService<Privatbank, PrivatbankRepository> {
	
	@Value("${api.privatbank}")
	private String apiUrl;

    public PrivatbankService(PrivatbankRepository repository) {
        super(repository);
    }

	public void loadRatesData() {
        RestTemplate restTemplate = new RestTemplate();
        List<Privatbank> entity = Arrays.asList(restTemplate.getForEntity(apiUrl, Privatbank[].class).getBody());
        entity.forEach(repository::save);
        log.info("Privatbank rates loaded success");
	}
}

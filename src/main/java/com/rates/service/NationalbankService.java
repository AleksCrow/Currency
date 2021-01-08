package com.rates.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rates.model.Nationalbank;
import com.rates.repository.NationalbankRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NationalbankService extends AbstractService<Nationalbank, NationalbankRepository> {
	
	@Value("${api.minfin}")
	private String apiUrl;

    public NationalbankService(NationalbankRepository repository) {
        super(repository);
    }
	
    @Override
	public void loadRatesData() {
        RestTemplate restTemplate = new RestTemplate();
        List<Nationalbank> entity = Arrays.asList(restTemplate.getForEntity(apiUrl, Nationalbank[].class).getBody());
        List<Nationalbank> ratesListFromDb = repository.findAll();
	if (ratesListFromDb.stream().noneMatch(entity::contains)) {
		entity.forEach(repository::save);
	}
        log.info("Nationalbank rates loaded success");
	}
}

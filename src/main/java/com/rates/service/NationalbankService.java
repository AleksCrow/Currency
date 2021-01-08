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
    	
//    	List<Nationalbank> list = natBankInit();
//    	list.forEach(repository::save);
	}
    
//    private List<Nationalbank> natBankInit() {
//		List<Nationalbank> list = new ArrayList<>();
//		list.add(new Nationalbank(1L, "rub", LocalDateTime.parse("2021-01-06T17:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "0.3835", "0.3839"));
//		list.add(new Nationalbank(2L, "eur", LocalDateTime.parse("2021-01-06T17:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "34.7832", "34.8050"));
//		list.add(new Nationalbank(3L, "usd", LocalDateTime.parse("2021-01-06T17:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "28.2400", "28.2600"));
//		
//		return list;
//	}
}

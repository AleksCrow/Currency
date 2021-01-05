package com.rates.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rates.model.Monobank;
import com.rates.service.MonobankService;

@RestController
@RequestMapping("/mono")
public class MonobankController extends AbstractController<Monobank, MonobankService> {

	public MonobankController(MonobankService service) {
		super(service);
	}

	@Override
	@GetMapping("/rates")
	public List<Monobank> getRates() {
		return super.getRates();
	}
	
	@Override
	@GetMapping("/get")
	public List<Monobank> loadRatesData() {
		return super.loadRatesData();
	}

	@Override
	@GetMapping("/update")
	public void load() {
		super.load();
	}
}

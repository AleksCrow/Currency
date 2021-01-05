package com.rates.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rates.model.Privatbank;
import com.rates.service.PrivatbankService;

@RestController
@RequestMapping("/privat")
public class PrivatbankController extends AbstractController<Privatbank, PrivatbankService> {

	public PrivatbankController(PrivatbankService service) {
		super(service);
	}
	
	@Override
	@GetMapping("/rates")
	public List<Privatbank> getRates() {
		return super.getRates();
	}
	
	@Override
	@GetMapping("/get")
	public List<Privatbank> loadRatesData() {
		return super.loadRatesData();
	}

	@Override
	@GetMapping("/update")
	public void load() {
		super.load();
	}
}

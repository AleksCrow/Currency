package com.rates.providers.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rates.providers.model.Nationalbank;
import com.rates.providers.model.Privatbank;
import com.rates.providers.service.NationalbankService;
import com.rates.providers.service.PrivatbankService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/minfin")
public class NationalbankController extends AbstractController<Nationalbank, NationalbankService> {

	public NationalbankController(NationalbankService service) {
		super(service);
	}
	
	@Override
	@GetMapping("/rates")
	public List<Nationalbank> getRates() {
		return super.getRates();
	}
	
	@Override
	@GetMapping("/filter")
	public List<Nationalbank> findBetween(@RequestParam(value = "startTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime starTime, @RequestParam(value = "endTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
		return super.findBetween(starTime, endTime);
	}

	@Override
	@GetMapping("/update")
	public void loadRatesData() {
		super.loadRatesData();
	}
}

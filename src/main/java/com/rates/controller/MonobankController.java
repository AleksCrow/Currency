package com.rates.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rates.model.Monobank;
import com.rates.service.MonobankService;

import lombok.extern.slf4j.Slf4j;

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
	@GetMapping("/filter")
	public List<Monobank> findBetween(
			@RequestParam(value = "startTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime starTime,
			@RequestParam(value = "endTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  endTime) {
		return super.findBetween(starTime, endTime);
	}
	
	@Override
	@GetMapping("/update")
	public void loadRatesData() {
		super.loadRatesData();
	}
}

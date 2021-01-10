package com.rates.average.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rates.average.model.AverageRate;
import com.rates.average.service.AverageRateService;

@RestController
@RequestMapping("/average")
public class AverageRateController {

	private final AverageRateService service;
	
	protected AverageRateController(AverageRateService service) {
		this.service = service;
	}

	@GetMapping(value = "/update")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void loadRatesData(){
		service.loadRatesData();
	}
	
	@GetMapping(value = "/rates", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AverageRate> getRates(){
		return service.getRates();
	}
	
	@GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AverageRate> getBetween(@RequestParam(value = "startTime", required = false) 
										@DateTimeFormat(iso = ISO.DATE_TIME) 
										LocalDateTime startTime, 
										@RequestParam(value = "endTime", required = false) 
										@DateTimeFormat(iso = ISO.DATE_TIME) 
										LocalDateTime endTime){
		return service.getAverageBetween(startTime, endTime);
	}
}

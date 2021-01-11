package com.rates.schedule;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rates.average.controller.AverageRateController;
import com.rates.average.service.AverageRateService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {
	
	private final AverageRateService service;
	
	protected ScheduledTasks(AverageRateService service) {
		this.service = service;
	}

	@Scheduled(cron = "0 0 6,18 * * *")
	public void updateData() {
		service.loadRatesData();
		log.info("Update time is: {}", LocalDateTime.now());
	}
}

package com.rates.schedule;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rates.average.controller.AverageRateController;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {
	
	private final AverageRateController controller;
	
	protected ScheduledTasks(AverageRateController controller) {
		this.controller = controller;
	}

	@Scheduled(cron = "0 0 6,18 * * *")
	public void updateData() {
		controller.loadRatesData();
		log.info("Update time is: {}", LocalDateTime.now());
	}
}

package com.example.demo.scheduledtasks;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoggerTask {
	
    // ===========================================
    // Public Methods
    // ===========================================
	
	@Scheduled(fixedRate = 5000)
	public void logCurrentTime() {
		System.out.println("The time is currently: " + new Date());
	}
}
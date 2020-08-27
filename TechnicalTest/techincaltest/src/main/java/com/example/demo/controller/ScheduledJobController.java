package com.example.demo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.scheduledtasks.LoggerTask;
import com.example.demo.scheduledtasks.PingTask;
import com.example.demo.scheduledtasks.WeatherTask;

@RestController
public class ScheduledJobController {
	
    // ===========================================
    // Private Members
    // ===========================================
	
	@Autowired
	private ScheduledAnnotationBeanPostProcessor scheduledTaskProcessorBean;
	
	@Autowired
	private LoggerTask loggerTask;
	
	@Autowired 
	private PingTask pingTask;
	
	@Autowired
	private WeatherTask weatherTask;
		
    // ===========================================
    // Public Methods
    // ===========================================
	
	// START THE LOGGER SCHEDULED TASK
	@PostMapping("/startLoggerTask")
	public String startLogger(String request) {
		scheduledTaskProcessorBean.postProcessAfterInitialization(loggerTask, "");
		return "OK";
	}
	
	// STOP THE LOGGER SCHEDULED TASK
	@PostMapping("/stopLoggerTask")
	public String stopLogger(String request) {
		scheduledTaskProcessorBean.postProcessBeforeDestruction(loggerTask, "");
		return "OK";
	}
	
	// START THE PING SCHEDULED TASK
	@PostMapping("/startPingTask")
	public String startPingTask(String request) {
		scheduledTaskProcessorBean.postProcessAfterInitialization(pingTask, "");
		return "OK";
	}
	
	// STOP THE PING SCHEDULED TASK
	@PostMapping("/stopPingTask")
	public String stopPingTask(String request) {
		scheduledTaskProcessorBean.postProcessBeforeDestruction(pingTask, "");
		return "OK";
	}
	
	// START THE WEATHER SCHEDULED TASK
	@PostMapping("/startWeatherTask")
	public String startWeatherTask(String request) {
		scheduledTaskProcessorBean.postProcessAfterInitialization(weatherTask, "");
		return "OK";
	}
	
	// STOP THE WEATHER SCHEDULED TASK
	@PostMapping("/stopWeatherTask")
	public String stopWeatherTask(String request) {
		scheduledTaskProcessorBean.postProcessBeforeDestruction(weatherTask, "");
		return "OK";
	}
	
	// RETURN ALL CURRENTLY RUNNING SCHEDULED TASKS
	@GetMapping("/getAllRunningTasks")
	public Set<ScheduledTask> getAllRunningTasks() {
		return scheduledTaskProcessorBean.getScheduledTasks();
	}
}

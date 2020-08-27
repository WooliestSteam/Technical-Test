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
	
	// SOME NOTES ABOUT THE FOLLOWING CLASS LOGIC
	
	// Using ScheduledAnnotationBeanPostProcessor to control the scheduled tasks.
	// This class autodetects any of the methods in the task classes which has the annotation @Scheduled 
	// This in turn then automatically handles the running of those methods through the in built task scheduler that comes with this class.
	
	// Using the postProcessAfterInitialization method which comes from the BeanPostProcessor interface through the ScheduledAnnotationBeanPostProcessor class.
	// This method allows me to handle the creation of new bean instances which is helpful in this case as I want to "resume/create a new instance" of a scheduled task.
	
	// Using the postProcessBeforeDestruction method which comes from the BeanPostProcessor interface through the ScheduledAnnotationBeanPostProcessor class.
	// This method handles the destruction of beans or in this case for this example, the "pausing" of beans. When I want to resume a task I will simply create a new bean instance.

	// Both of these methods take two params, 1st which is the actual bean I want to create/destroy and 2nd is a String bean name, this param is never used however so I have left this blank in the class.
	
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

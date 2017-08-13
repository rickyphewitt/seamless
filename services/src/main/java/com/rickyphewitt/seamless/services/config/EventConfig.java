package com.rickyphewitt.seamless.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.support.TaskUtils;

@Configuration
public class EventConfig {

	   @Bean
	    ApplicationEventMulticaster applicationEventMulticaster() {
	        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
	        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
	        eventMulticaster.setErrorHandler(TaskUtils.LOG_AND_SUPPRESS_ERROR_HANDLER);
	        return eventMulticaster;
	    }
}

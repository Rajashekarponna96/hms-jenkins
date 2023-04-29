package com.spdx.hms.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration {
	@Value("${corePoolSize}")
	private Integer corePoolSize;

	@Value("${maxPoolSize}")
	private Integer maxPoolSize;

	@Value("${queueCapacity}")
	private Integer queueCapacity;

	@Value("${threadName}")
	private String threadName;

	@Bean(name = "asyncExecutor")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix(threadName);
		executor.initialize();
		return executor;
	}
	
	
}

package com.github.binarywang.demo.wx.mp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName:     ThreadExecutePool.java
 * @Description:   TODO(公共配置，配置线程池, 可配置多个线程池) 
 * 
 * @author          zhangjianjun
 * @version         V1.0  
 * @Date           Jun 30, 2019 10:48:43 AM 
 * 
 * 
 * 公共配置 可放到 common 里面
 */
@Configuration
public class ThreadExecutePoolConfig {
	  //设置核心线程数
	  @Value("${nexus.pool.core.size:150}")
	  private Integer corePoolSize;
	  
	  //设置最大线程数
	  @Value("${nexus.pool.max.size:300}")
	  private Integer maxPoolSize;
	  
	  //线程池所使用的缓冲队列
	  @Value("${nexus.pool.queue.capacity:100}")
	  private Integer queueCapacity;
	  
	  //允许线程池空闲时间
	  @Value("${nexus.pool.queue.keep.alive.second:10}")
	  private Integer keepAliveSeconds;

	 @Bean(name ="threadPoolTaskExecutor")
	 public Executor threadPoolTaskExecutOrTask() {
	 ThreadPoolTaskExecutor executor =new ThreadPoolTaskExecutor();
	         executor.setCorePoolSize(corePoolSize);

	         executor.setMaxPoolSize(maxPoolSize);

	         executor.setQueueCapacity(queueCapacity);

	         executor.setKeepAliveSeconds(keepAliveSeconds);

	         executor.setThreadNamePrefix("Pool-Nexus");
	         
	         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

	         executor.initialize();
	         

	         return executor;

	     }

	 @Bean(name ="schedulePoolTaskExecutor")
	 public Executor scheduleTaskExecutOrTask() {

		 ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

		 threadPoolTaskScheduler.setPoolSize(corePoolSize);

		 threadPoolTaskScheduler.setThreadNamePrefix("Schedule-1");

		 threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);

		 threadPoolTaskScheduler.setAwaitTerminationSeconds(300);

		 threadPoolTaskScheduler.initialize();

		 return threadPoolTaskScheduler;


	 }



}

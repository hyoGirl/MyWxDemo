package com.github.binarywang.demo.wx.mp.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Auther: xulei
 * @Date: 2019/7/15 0015 14:55
 * @Description:
 */
@Configuration
public class MyThreadConfig implements AsyncConfigurer {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${thread.pool.coreSize:10}")
    private int coreSize;

    @Value("${thread.pool.maxSize:100}")
    private int maxSize;

    @Value("${thread.pool.keepAliveTime:10}")
    private int keepAliveTime;

    @Value("${thread.pool.queueCapacity:10}")
    private int queueCapacity;


    @Bean("springThread")
    public Executor springThread() {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(coreSize);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveTime);
        threadPoolTaskExecutor.setMaxPoolSize(maxSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setThreadNamePrefix("myThread-");

        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();


        return threadPoolTaskExecutor;

    }

    /**
     * 异步任务中异常处理
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {

            @Override
            public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
                logger.error("==========================" + arg0.getMessage() + "=======================", arg0);
                logger.error("exception method:" + arg1.getName());
            }
        };
    }



    @Primary
    @Bean( name = "myThread",autowire= Autowire.BY_NAME)
//    @Component("myThread")
    public ThreadPoolExecutor myThread() {

        int corePoolSize = coreSize;
        int maximumPoolSize = maxSize;
        TimeUnit unit = TimeUnit.SECONDS;


        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(queueCapacity);


        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                new NameTreadFactory(), callerRunsPolicy
        );
        return threadPoolExecutor;


    }

//
//    @Bean(name = "myThread2")
//    public ThreadPoolExecutor myThread2() {
//
//        int corePoolSize = coreSize;
//        int maximumPoolSize = maxSize;
//        TimeUnit unit = TimeUnit.SECONDS;
//
//
//        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(queueCapacity);
//
//        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
//
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
//                maximumPoolSize,
//                keepAliveTime,
//                unit,
//                workQueue,
//                new NameTreadFactory(), callerRunsPolicy
//        );
//        return threadPoolExecutor;
//
//
//    }
//
    class NameTreadFactory implements ThreadFactory {


        private final AtomicInteger threadNumber = new AtomicInteger(1);
//        private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

        @Override
        public Thread newThread(Runnable r) {

//            Thread thread = this.defaultFactory.newThread(r);

            Thread thread = new Thread(r, "Java-" + this.threadNumber.getAndIncrement());
            return thread;
        }
    }


    @Bean("")
    public ScheduledExecutorService getExecutor(){

//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,new BasicThreadFactory.Builder().namingPattern("scheduled-pool-%d").build());


        return scheduledExecutorService;
    }



}







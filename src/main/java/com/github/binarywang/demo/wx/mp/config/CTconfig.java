package com.github.binarywang.demo.wx.mp.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ashura1110
 * @ClassName XULEI
 * @description TODO
 * @Date 2019/7/29 23:59
 * @Version 1.0
 */
@Configuration
public class CTconfig {


    @Bean(name = "myThread3",autowire= Autowire.BY_NAME)
    public ScheduledThreadPoolExecutor myThread3() {


        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(10,new NameTreadFactory2(),callerRunsPolicy);

        return poolExecutor;


    }


    class NameTreadFactory2 implements ThreadFactory {


        private final AtomicInteger threadNumber = new AtomicInteger(1);
//        private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

        @Override
        public Thread newThread(Runnable r) {

//            Thread thread = this.defaultFactory.newThread(r);

            Thread thread = new Thread(r, "Schedule-" + this.threadNumber.getAndIncrement());
            return thread;
        }
    }
}

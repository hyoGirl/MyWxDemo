package com.java.test.JDK8;

import com.github.binarywang.demo.wx.mp.config.MyThreadConfig;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ashura1110
 * @ClassName XULEI
 * @description TODO
 * @Date 2019/7/29 23:25
 * @Version 1.0
 */
public class Test {


    public static void main(String[] args) {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                20,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10)
        );




        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(10, callerRunsPolicy);


    }
}

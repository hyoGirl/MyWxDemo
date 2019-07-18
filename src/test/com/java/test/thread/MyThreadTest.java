package com.java.test.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: xulei
 * @Date: 2019/7/18 0018 13:44
 * @Description:
 */
public class MyThreadTest {

    public static void main(String[] args) {


        int i = Runtime.getRuntime().availableProcessors();

        System.out.println(i);


        ExecutorService executorService = Executors.newFixedThreadPool(20);


        for (int j = 0; j < 20; j++) {

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(name);
                }
            });

        }
    }
}

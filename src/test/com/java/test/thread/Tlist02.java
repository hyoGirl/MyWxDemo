package com.java.test.thread;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther: xulei
 * @Date: 2019/7/15 0015 14:16
 * @Description:
 */
public class Tlist02 {


    public static void main(String[] args) throws  Exception{
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("ASDF"+i);
        }
        int count=list.size();
        //每5个进行处理
        int threadCount=5;
        int num=count%threadCount==0?count/threadCount:count/threadCount+1;
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        // 定义一个任务集合
        List<Callable<List<People>>> tasks = new ArrayList<Callable<List<People>>>();
        Callable<List<People>> task = null;
        List<String> subList=null;
        for (int i = 0; i < num; i++) {
            if(i==num-1){
                 subList = list.subList(i*threadCount, count);
            }else{
                subList = list.subList(i*threadCount, (i+1)*threadCount);
            }
            final List<String> listStr=subList;
            task=new Callable<List<People>>() {
                @Override
                public List<People> call() throws Exception {
                    List<People> pp=new ArrayList<>();
                    for (int j = 0; j < listStr.size(); j++) {
                        People people=new People();
                        people.setName(listStr.get(j));
                        pp.add(people);
                    }
                    return pp;
                }
            };
            tasks.add(task);
        }
        List<Future<List<People>>> futureList = executorService.invokeAll(tasks);
        List result=new ArrayList();
        for(Future<List<People>> future:futureList){
            result.add(future.get());
        }
        System.out.println(JSON.toJSON(result));
    }
}

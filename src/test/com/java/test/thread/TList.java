package com.java.test.thread;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther: xulei
 * @Date: 2019/7/15 0015 13:38
 * @Description:
 */
public class TList {


    public static void main(String[] args) throws  Exception{

        List<String> list=new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            list.add("ASDF"+i);
        }

        int count=list.size();


        int threadCount=5;


        int num=count%threadCount==0?count/threadCount:count/threadCount+1;

        ExecutorService executorService = Executors.newFixedThreadPool(num);
        // 定义一个任务集合
        List<Callable<List<People>>> tasks = new ArrayList<Callable<List<People>>>();

        Callable<List<People>> task = null;

        int temp=0;
        int temp2=0;
        for (int i = 0; i < num; i++) {
            temp=i*threadCount;
            temp2=temp+threadCount;
            if(i!=0){
                temp=i*threadCount+1;
            }
            List<String> subList = list.subList(temp, temp2);

            task= new Callable<List<People>>() {
                @Override
                public List<People> call() throws Exception {
                    List<People> pp=new ArrayList<>();
                    for (int j = 0; j < subList.size(); j++) {
                        People people=new People();
                        people.setName(subList.get(j));
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

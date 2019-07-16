package com.java.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xulei
 * @Date: 2019/7/6 0006 11:27
 * @Description:
 */
public class Test80w {

    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long l = runtime.freeMemory();
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            String ss="ASDFG"+i;
            list.add(ss);
        }
        long l1 = l-runtime.freeMemory() ;
        long l2 = l1 / 1024 / 1024;
        System.out.println(l1);
        System.out.println(l2);

//        System.out.println(list.size());


    }

}

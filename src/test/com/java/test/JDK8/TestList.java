package com.java.test.JDK8;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xulei
 * @Date: 2019/7/8 0008 17:33
 * @Description:
 */
public class TestList {


    public static void main(String[] args) {


        List<Object> ls=new ArrayList<>();


        ls.add("asd11");
        ls.add("asd12");
        ls.add("asd13");
        ls.add("asd14");


        Object obj=ls;

        List<String> list= (List<String>) obj;


        System.out.println(list.toString());
    }
}

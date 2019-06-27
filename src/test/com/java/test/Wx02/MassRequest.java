package com.java.test.Wx02;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xulei
 * @Date: 2019/6/19 0019 20:13
 * @Description:
 */
public class MassRequest {


    public static void main(String[] args) {

        Date date=new Date();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        System.out.println(simpleDateFormat.format(date));
    }
}

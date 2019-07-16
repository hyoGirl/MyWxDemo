package com.java.test.JDK8;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Auther: xulei
 * @Date: 2019/7/6 0006 11:09
 * @Description:
 */
public class TestDate {


    public static void main(String[] args) {

        method1();

    }

    /**
     * 1:获取年月日时间格式
     * 2：获取时分秒格式
     * 3：获取年月日时分秒格式
     */
    private static void method1() {

        LocalDate now = LocalDate.now();
        //2019-07-06
        System.out.println("年月日时间为： "+now);

        System.out.println();

        LocalTime localTimenow = LocalTime.now(); //此刻的时间--精确到毫秒


        System.out.println("时分秒毫秒的时间为： "+localTimenow);
        //11:17:35.240


        LocalDateTime localDateTimenow = LocalDateTime.now(); //此刻时间--年-月-日-时-分-秒-毫秒


        System.out.println("年-月-日-时-分-秒-毫秒: "+localDateTimenow);
        //2019-07-06T11:17:35.240

        System.out.println();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String format = localDateTimenow.format(formatter);


        System.out.println("格式化后的时间为： "+format);


        Date time=new Date();

        Instant instant = time.toInstant();

        ZoneId zoneId=ZoneId.systemDefault();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);


        System.out.println("Date 转换的 localDateTime-->"+localDateTime);
    }


}

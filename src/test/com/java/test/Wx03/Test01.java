package com.java.test.Wx03;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Auther: xulei
 * @Date: 2019/6/22 0022 16:33
 * @Description:
 */
public class Test01 {

    public static void main(String[] args) {

        String time = "2019-10-09 10:55:30";

        DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//        LocalDate parse = LocalDate.parse(time, sf);


        LocalDateTime parse = LocalDateTime.parse("2018-10-18 10:01:00", sf);

        ZoneId zoneId = ZoneId.systemDefault();

        //转换为当地时间
        ZonedDateTime zonedDateTime = parse.atZone(zoneId);
        //转为Date类型
        Date from = Date.from(zonedDateTime.toInstant());

        System.out.println(from);



    }
}

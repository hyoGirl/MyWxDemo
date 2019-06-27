package com.java.test.Wx02;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xulei
 * @Date: 2019/6/19 0019 20:06
 * @Description:
 */
public class TestMass {

    public static void main(String[] args) {


        List<Mass>  list=new ArrayList<Mass>();


        Mass mass=new Mass();
        mass.setCount(100);
        mass.setName("双11活动");
        mass.setMsgType("图文消息");
        mass.setReceiver(1);
        mass.setReceiver_tag("群组01");
        mass.setSendStatus(1);
        mass.setSendTime(new Date());

        Mass mass2=new Mass();
        mass2.setCount(100);
        mass2.setName("双12活动");
        mass2.setMsgType("图片");
        mass2.setReceiver(1);
        mass2.setReceiver_tag("群组02");
        mass2.setSendStatus(1);
        mass2.setSendTime(new Date());

        list.add(mass);
        list.add(mass2);

        System.out.println(JSON.toJSON(list));

        Map map=new HashMap();
        map.put("total",1000);
        map.put("rows",JSON.toJSON(list));
        map.put("code",200);
        map.put("msg","操作成功");

        System.out.println(JSON.toJSON(map));

    }
}

package com.java.test.Wx;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: xulei
 * @Date: 2019/6/19 0019 15:18
 * @Description:
 */
public class TestJson {

    public static void main(String[] args) {

        MassDto massDto=new MassDto();


        massDto.setActive(1);
        massDto.setCheckState(1);
        massDto.setReceiver(1);

        massDto.setSendNow(1);
        massDto.setSendTime(new Date());



        List<Msg> msgs=new ArrayList<Msg>();


        Msg  msg=new Msg();
        msg.setContent("中午好");
        msg.setMediaId("132321312312");
        msg.setMsgType("图文");
        msg.setReceiver_tag("群组1");
        msg.setCount(1000);


        Msg  msg2=new Msg();
        msg2.setContent("www.baidu.com");
        msg2.setMediaId("1300000000");
        msg2.setMsgType("图片");
        msg2.setReceiver_tag("群组2");
        msg2.setCount(10000);

        msgs.add(msg);
        msgs.add(msg2);

        massDto.setMsgs(msgs);



        System.out.println(JSON.toJSON(massDto));


    }


}

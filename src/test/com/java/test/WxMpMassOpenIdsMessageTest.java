package com.java.test;

import com.alibaba.fastjson.JSON;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import sun.misc.resources.Messages_sv;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xulei
 * @Date: 2019/7/6 0006 14:50
 * @Description:
 */
public class WxMpMassOpenIdsMessageTest {


    public static void main(String[] args) {


        WxMpMassOpenIdsMessage message=new WxMpMassOpenIdsMessage();


        message.setClientMsgId(System.currentTimeMillis()+"");

        message.setSendIgnoreReprint(true);

        message.setContent("自己机器测试");

        message.setMsgType("text");




        List<String> list=new ArrayList<>();



        list.add("oNPk05kkQm3IijnuzxP89_9GpYYY");
        list.add("oNPk05koDKHtPhw-KIWUBQLAFvJc");
        list.add("oNPk05reXenJyIJ5sYCaq96bwAfk");
        list.add("oNPk05j3qb4jomVNr6mMyosL_YLM");
        list.add("oNPk05uzoxnOgeMlOx1K5luBoutA");
        list.add("oNPk05iDPHeQlG5h7Jdti4JNmbjM");

        message.setToUsers(list);

        message.setClientMsgId(System.currentTimeMillis()+"");


        System.out.println(JSON.toJSON(message));

    }
}

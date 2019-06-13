package com.java.test;

import com.alibaba.fastjson.JSON;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ashur
 * @Date: 2019/6/13 0013 15:57
 * @Description:
 */
public class Test02 {

    public static void main(String[] args) {


        WxMpMassOpenIdsMessage wxMpMassOpenIdsMessage=new WxMpMassOpenIdsMessage();


        wxMpMassOpenIdsMessage.setClientMsgId("");

        wxMpMassOpenIdsMessage.setContent("");

        wxMpMassOpenIdsMessage.setMsgType("image");

        wxMpMassOpenIdsMessage.setSendIgnoreReprint(false);



        List<String> list=new ArrayList();
        list.add("oNPk05j3qb4jomVNr6mMyosL_YLM");
        list.add("oNPk05oISG3HH_A2oZU1bQ5aA1nw");


        wxMpMassOpenIdsMessage.setToUsers(list);

        wxMpMassOpenIdsMessage.setMediaId("zavg5zkBkBDFP1bvcDTVFG3t8dA5W67HRGkkPOaihyS191B34e3FIZd5QPG3MY_3");

        System.out.println(JSON.toJSON(wxMpMassOpenIdsMessage));

    }
}

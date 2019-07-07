package com.github.binarywang.demo.wx.mp.service;

import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ashura1110
 * @ClassName XULEI
 * @description TODO
 * @Date 2019/6/28 21:43
 * @Version 1.0
 */
@Service
public class SendMsgService {


    @Autowired
    WxMpKefuService wxMpKefuService;


    public void sendMsg(String openid)throws  Exception{
        System.out.println("-------------开始进入自己逻辑中-----------------");
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent("hello 这个是客服消息");
        wxMpKefuMessage.setToUser(openid);
        wxMpKefuMessage.setMsgType("text");


        wxMpKefuService.sendKefuMessage(wxMpKefuMessage);

    }
}

package com.github.binarywang.demo.wx.mp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: xulei
 * @Date: 2019/6/29 0029 11:55
 * @Description:
 */
@Service
public class MyService {


    @Autowired
    WxMpKefuService wxMpKefuService;


    public void  doMethod(String fromUser){



    }

}

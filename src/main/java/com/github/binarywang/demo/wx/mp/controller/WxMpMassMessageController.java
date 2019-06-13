package com.github.binarywang.demo.wx.mp.controller;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMassMessageService;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ashur
 * @Date: 2019/6/13 0013 15:50
 * @Description:
 */
@AllArgsConstructor
@RestController
@RequestMapping("/wx/message")
public class WxMpMassMessageController {


    @Autowired
    WxMpMassMessageService wxMpMassMessageService;


    @PostMapping("/send")
    @ResponseBody
    public String massOpenIdsMessageSend(@RequestBody WxMpMassOpenIdsMessage message){

        WxMpMassSendResult wxMpMassSendResult=null;
        try {
             wxMpMassSendResult = wxMpMassMessageService.massOpenIdsMessageSend(message);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(wxMpMassSendResult);
    }

}

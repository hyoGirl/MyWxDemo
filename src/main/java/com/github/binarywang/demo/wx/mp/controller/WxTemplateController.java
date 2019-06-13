package com.github.binarywang.demo.wx.mp.controller;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.demo.wx.mp.vo.TemplateVo;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: ashur
 * @Date: 2019/6/12 0012 15:00
 * @Description:
 */
@AllArgsConstructor
@Controller
@RequestMapping("/wx/template")
public class WxTemplateController {

    @Autowired
    WxMpTemplateMsgService wxMpTemplateMsgService;


    @PostMapping("/send")
    @ResponseBody
    public String sendTemplateData(@RequestBody  WxMpTemplateMessage  templateMessage){

        String msg="";
        try {
             msg = wxMpTemplateMsgService.sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return msg;
    }


}

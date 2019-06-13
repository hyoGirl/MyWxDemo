package com.github.binarywang.demo.wx.mp.controller;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.demo.wx.mp.utils.MyFileUtils;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @Auther: ashur
 * @Date: 2019/6/13 0013 11:45
 * @Description:
 */
@AllArgsConstructor
@Controller
@RequestMapping("/wx/qrcode")
public class WxMpQrcodeController {


    @Autowired
    WxMpQrcodeService  wxMpQrcodeService;


    /**
     *  换取临时二维码ticket
     * @param sceneId
     * @param expireSeconds
     * @return
     */
    @PostMapping("/ticket")
    @ResponseBody
    public  String qrCodeCreateTmpTicket(@RequestParam("sceneId") int sceneId, @RequestParam("expireSeconds") Integer expireSeconds){

        WxMpQrCodeTicket wxMpQrCodeTicket=null;

        try {
            wxMpQrCodeTicket = wxMpQrcodeService.qrCodeCreateTmpTicket(sceneId, expireSeconds);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(wxMpQrCodeTicket);
    }

    /**
     * 通过ticket来换二维码图片
     */
    @PostMapping("/qrCodePicture")
    @ResponseBody
    public void qrCodePicture(@RequestBody WxMpQrCodeTicket ticket, HttpServletResponse response){

        try {
            File file = wxMpQrcodeService.qrCodePicture(ticket);
            MyFileUtils.download(file,  response);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

    }






}

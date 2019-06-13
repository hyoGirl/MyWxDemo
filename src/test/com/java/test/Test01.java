package com.java.test;

import com.alibaba.fastjson.JSON;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ashur
 * @Date: 2019/6/12 0012 16:43
 * @Description:
 */
public class Test01 {


    public static void main(String[] args) {

        WxMpTemplateMessage wxMpTemplateMessage=new WxMpTemplateMessage();

        wxMpTemplateMessage.setUrl("http://");

        wxMpTemplateMessage.setToUser("12345");

        wxMpTemplateMessage.setTemplateId("0123456");




        List<WxMpTemplateData> data=new ArrayList<>();

        WxMpTemplateData wxMpTemplateData01=new WxMpTemplateData();
        wxMpTemplateData01.setColor("#173177");
        wxMpTemplateData01.setName("");
        wxMpTemplateData01.setValue("恭喜你购买成功！");

        WxMpTemplateData wxMpTemplateData02=new WxMpTemplateData();
        wxMpTemplateData02.setColor("#173177");
        wxMpTemplateData02.setName("商品名称：");
        wxMpTemplateData02.setValue("巧克力！");

        WxMpTemplateData wxMpTemplateData03=new WxMpTemplateData();
        wxMpTemplateData03.setColor("#173177");
        wxMpTemplateData03.setName("消费金额：");
        wxMpTemplateData03.setValue("3900元");

        WxMpTemplateData wxMpTemplateData04=new WxMpTemplateData();
        wxMpTemplateData04.setColor("#173177");
        wxMpTemplateData04.setName("购买时间：");
        wxMpTemplateData04.setValue("2019/09/19");

        data.add(wxMpTemplateData01);
        data.add(wxMpTemplateData02);
        data.add(wxMpTemplateData03);
        data.add(wxMpTemplateData04);


        wxMpTemplateMessage.setData(data);

        WxMpTemplateMessage.MiniProgram miniProgram=new WxMpTemplateMessage.MiniProgram();


        miniProgram.setAppid("123");
        miniProgram.setPagePath("000");
        miniProgram.setUsePath(false);


        System.out.println(JSON.toJSON(wxMpTemplateMessage));


    }
}

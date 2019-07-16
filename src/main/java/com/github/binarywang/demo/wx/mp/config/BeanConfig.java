package com.github.binarywang.demo.wx.mp.config;

import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpMassMessageService;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.api.impl.WxMpKefuServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpMassMessageServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpMaterialServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpQrcodeServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpTemplateMsgServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

/**
 * @Auther: ashur
 * @Date: 2019/6/11 0011 17:16
 * @Description:
 */
@Configuration
public class BeanConfig  {
    @Autowired
    WxMpService wxMpService;
    @Bean
    public WxMpUserService wxMpUserService(){
        WxMpUserService wxMpUserService=new WxMpUserServiceImpl(wxMpService);
        return wxMpUserService;
    }

    @Bean
    public WxMpMaterialService wxMpMaterialService(){

        WxMpMaterialService wxMpMaterialService=new WxMpMaterialServiceImpl(wxMpService);
        return wxMpMaterialService;
    }

    @Bean
    public WxMpTemplateMsgService wxMpTemplateMsgService(){

        WxMpTemplateMsgService wxMpTemplateMsgService=new WxMpTemplateMsgServiceImpl(wxMpService);

        return wxMpTemplateMsgService;
    }

    @Bean
    public WxMpQrcodeService wxMpQrcodeService(){

        WxMpQrcodeService wxMpQrcodeService=new WxMpQrcodeServiceImpl(wxMpService);

        return wxMpQrcodeService;
    }

    @Bean
    public WxMpMassMessageService wxMpMassMessageService(){

        System.out.println("=====================群发service开始注入了");
        WxMpMassMessageService wxMpMassMessageService=new WxMpMassMessageServiceImpl(wxMpService);

        return wxMpMassMessageService;
    }

    @Bean
    public WxMpKefuService wxMpKefuService(){

        System.out.println("---------客服service实例化");

        WxMpKefuService wxMpKefuService=new WxMpKefuServiceImpl(wxMpService);
        return wxMpKefuService;
    }

}

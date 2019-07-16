package com.github.binarywang.demo.wx.mp.handler;

import java.util.Map;

import com.github.binarywang.demo.wx.mp.service.MyService;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.binarywang.demo.wx.mp.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@Order(2)
public class SubscribeHandler extends AbstractHandler {


    volatile  boolean flag=false;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService()
                .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                System.out.println(userWxInfo.toString());
                // TODO 可以添加关注用户到本地数据库
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        }


        /**
         *
         * 测试使用客服service发送
         *
         *
         */
        String fromUser = wxMessage.getFromUser();

        WxMpKefuService kefuService = weixinService.getKefuService();
//
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(flag){
                    WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();

                    wxMpKefuMessage.setToUser(wxMessage.getFromUser());

                    wxMpKefuMessage.setMsgType("text");
                    wxMpKefuMessage.setContent("测试发送第二条记录");
                    try {
                        Thread.sleep(2000);
                        kefuService.sendKefuMessage(wxMpKefuMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }
       changeFlag(flag);

        try {
            return new TextBuilder().build("感谢关注", wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    private void changeFlag(boolean flag) {
        flag=true;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {

        System.out.println(wxMessage.getEvent());
        System.out.println(wxMessage.getEventKey());


        System.out.println("用户扫码进来");
        //TODO
        return null;
    }

}

package com.github.binarywang.demo.wx.mp.controller;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.demo.wx.mp.entity.WxUser;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author ashura1110
 * @ClassName XULEI
 * @description TODO
 * @Date 2019/9/9 14:16
 * @Version 1.0
 */
@RestController
@RequestMapping("/wx/oauth")
@Slf4j
public class WeChatOauthController {


    @Autowired
    private WxMpService wxMpService;

    //首先是，H5请求我，我返回了一个微信授权登录链接地址，前端重定向到这个授权地址（这个是微信的界面）用户同意了，
    // 微信携带了code跳转到你的界面，然后我根据code去获取token，然后再获取信息，重定向到他配置的页面

//    https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID
//    &redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {

        String returnUrlEncode =  URLEncoder.encode(returnUrl,"UTF-8");
        String state= "STATE";
        //回调地址
        String requestUrl = "http://4gkije.natappfree.cc/wx/oauth/userInfo";
        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(requestUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO,state);
        log.info("【微信网页授权】获取redirectURL,redirectURL={}", redirectURL);
        return "redirect:" + redirectURL;
    }
//    @GetMapping("/userInfo")
//    public String userInfo(@RequestParam("code") String code,
//                           @RequestParam("returnUrl") String returnUrl) throws Exception {
    @GetMapping("/userInfo")
    public ModelAndView userInfo(@RequestParam("code") String code
                           ) throws Exception {
        log.info("【微信网页授权】code={}", code);
//        log.info("【微信网页授权】returnUrl={}", returnUrl);
        WxMpUser wxMpUser = null;
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String lang = "zh_CN";
            wxMpUser = this.wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, lang);
            log.info("【微信网页授权用户的基本信息】wxMpUser={}", JSON.toJSONString(wxMpUser));
        } catch (WxErrorException e) {
            log.info("【微信网页授权】{}", e);
            throw new Exception(e.getError().getErrorMsg());
        }
        return "redirect:" + returnUrl + "?wxMpUser=" + URLEncoder.encode(JSON.toJSONString(wxMpUser),"UTF-8");
//        return "redirect:" + "http://www.baidu.com" + "?wxMpUser=" + URLEncoder.encode(JSON.toJSONString(wxMpUser),"UTF-8");

    }
}

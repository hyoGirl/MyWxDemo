package com.github.binarywang.demo.wx.mp.controller;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.demo.wx.mp.config.MyThreadConfig;
import com.github.binarywang.demo.wx.mp.utils.JsonUtils;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther: ashur
 * @Date: 2019/6/11 0011 16:34
 * @Description:  用户管理相关操作接口
 */

@AllArgsConstructor
@Controller
@RequestMapping("/wx/user")
public class WxUserController {

    @Autowired
    WxMpUserService wxMpUserService;



    @Autowired
    MyThreadConfig myThreadConfig;





    /**
     * 如果关注者太多，那么每次去
     * 就是在调用接口时，将上一次调用得到的返回中的next_openid值，作为下一次调用中的next_openid值
     * 不填写默认就是拉取10000条记录
     * @param next_openid
     * @return
     */
    @GetMapping("/getUserList")
    @ResponseBody
    public String getUserList(@RequestParam(name = "next_openid", required = false) String next_openid) {
        long l = System.currentTimeMillis();
        WxMpUserList wxMpUserList=null;
        try {
            wxMpUserList = this.wxMpUserService.userList(next_openid);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        List<String> openids = wxMpUserList.getOpenids();

        List<WxMpUser> list=new ArrayList<>();

        try {
            for (int i = 0; i < openids.size(); i++) {
                String openid = openids.get(i);

                WxMpUser zh_cn = wxMpUserService.userInfo(openid, "zh_CN");
                list.add(zh_cn);

            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(list));
        System.out.println("总执行时间为： "+(System.currentTimeMillis()-l));

        return JSON.toJSONString(wxMpUserList);
    }

    @GetMapping("/getUserList2")
    @ResponseBody
    public String getUserList2(@RequestParam(name = "next_openid", required = false) String next_openid) throws Exception {

        long l = System.currentTimeMillis();
        WxMpUserList wxMpUserList=null;
        try {
            wxMpUserList = this.wxMpUserService.userList(next_openid);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        List<String> openids = wxMpUserList.getOpenids();
        int threadNum=5;
        int num=openids.size()%threadNum==0?openids.size()/threadNum:openids.size()%threadNum+1;
        List<String> subOpenids=null;
        Callable<List<WxMpUser>> task = null;
        List<Callable<List<WxMpUser>>> tasks = new ArrayList<Callable<List<WxMpUser>>>();
        for (int i = 0; i < num; i++) {
            if(i==num-1){
                subOpenids=openids.subList(i*threadNum,openids.size());
            }else{
                subOpenids=openids.subList(i*threadNum,(i+1)*threadNum);
            }
           final List<String> sublist=subOpenids;
            task=new Callable<List<WxMpUser>>() {
                @Override
                public List<WxMpUser> call() throws Exception {
                    List<WxMpUser> list=new ArrayList<>();
                    for (int j = 0; j < sublist.size(); j++) {
                        WxMpUser zh_cn = wxMpUserService.userInfo(sublist.get(j), "zh_CN");
                        list.add(zh_cn);
                    }
                    return list;
                }
            };
            tasks.add(task);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        List result=new ArrayList();
        List<Future<List<WxMpUser>>> futures = executorService.invokeAll(tasks);
        for(Future<List<WxMpUser>> future:futures){
            result.add(future.get());
        }
        System.out.println("总执行时间为： "+(System.currentTimeMillis()-l));
//        return JSON.toJSONString(result);

        System.out.println(JSON.toJSONString(result));
        return JSON.toJSONString(wxMpUserList);
    }




    /**
     * 获取用户基本信息
     * @param openid  用户openid
     * @param lang  语言，zh_CN 简体(默认)，zh_TW 繁体，en 英语
     * @return
     */
    @ResponseBody
    @PostMapping("/userInfo")
    public String userInfo(@RequestParam("openid") String openid,
                           @RequestParam("lang") String lang)  {
        WxMpUser wxMpUser=null;
        try {
            wxMpUser = this.wxMpUserService.userInfo(openid, lang);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return  JSON.toJSONString(wxMpUser);
    }

    /**
     * 获取用户基本信息列表
     * 开发者可通过该接口来批量获取用户基本信息。最多支持一次拉取100条。
     * @param openidList  用户openid列表
     * @return
     */
    @ResponseBody
    @PostMapping("/userInfoList")
    public String userInfo(@RequestParam("openidList") List<String> openidList)  {
        System.out.println(openidList.toString());
        List<WxMpUser> wxMpUsers=null;
        try {
             wxMpUsers = this.wxMpUserService.userInfoList(openidList);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return  JSON.toJSONString(wxMpUsers);
    }


    @PostMapping("/updateRemark")
    public void updateRemark(@RequestParam("openid") String openid
        ,@RequestParam("remark") String remark)  {
        try {
             this.wxMpUserService.userUpdateRemark(openid,remark);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}

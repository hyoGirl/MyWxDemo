package com.github.binarywang.demo.wx.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.demo.wx.mp.entity.WxUser;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import java.util.List;

/**
 * @Auther: xulei
 * @Date: 2019/8/6 0006 14:27
 * @Description:
 */
public interface WxUserService extends IService<WxUser> {

    void batchSaveDatas(List<WxMpUser> result);

}

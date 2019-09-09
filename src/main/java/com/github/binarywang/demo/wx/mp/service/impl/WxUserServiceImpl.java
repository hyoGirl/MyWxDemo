package com.github.binarywang.demo.wx.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.mp.dao.WxUserDao;
import com.github.binarywang.demo.wx.mp.entity.WxUser;
import com.github.binarywang.demo.wx.mp.service.WxUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: xulei
 * @Date: 2019/8/6 0006 14:28
 * @Description:
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserDao, WxUser> implements WxUserService {

    @Autowired
    WxUserDao wxUserDao;

    @Override
    public void batchSaveDatas(List<WxMpUser> result) {


        wxUserDao.saveDatas(result);

    }
}

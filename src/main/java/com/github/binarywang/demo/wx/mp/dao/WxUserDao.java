package com.github.binarywang.demo.wx.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.binarywang.demo.wx.mp.entity.WxUser;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: xulei
 * @Date: 2019/8/6 0006 14:29
 * @Description:
 */
@Mapper
public interface WxUserDao extends BaseMapper<WxUser> {

    void saveDatas(@Param("result") List<WxMpUser> result);
}

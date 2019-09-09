package com.github.binarywang.demo.wx.mp.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zheng.yuan
 * @Date: 2019/6/26 14:12
 * @Description:
 */
@Data
@TableName(value = "t_wx_user")
public class WxUser implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Boolean subscribe;

    private String labels;

    @TableField(value = "open_id")
    private String openId;

    @TableField(value = "sex")
    private Integer sex;

    @TableField(value = "nick_name")
    private String nickName;

    private String city;

    private String province;

    private String country;

    private String language;

    @TableField(value = "head_img_url")
    private String headImgUrl;

    @TableField(value = "subscribe_time")
    private Long subscribeTime;

    @TableField(value = "union_id")
    private String unionId;

    private String remark;

    @TableField(value = "subscribe_scene")
    private String subscribeScene;

    @TableField(value = "qr_scene")
    private Integer qrScene;

    @TableField(value = "qr_scene_str")
    private String qrSceneStr;

    @TableField(value = "active_time")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activeTime;

    private String ext2;

    @TableField(value = "interaction_count")
    private Integer interactionCount;

    @TableField(value = "score_count")
    private Integer scoreCount;

    @TableField(value = "focus_count")
    private Integer focusCount;

    @TableField(value = "cancel_count")
    private Integer cancelCount;

   // @ApiModelProperty(value = "是否有效：1-有效，0-无效", name = "enable", dataType = "Integer", example = "1")
    private Integer enable;

    @TableField(value = "create_time")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "update_time")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(exist = false)
    private String region;

    @TableField(exist = false)
    private List<String> labelArray;
}

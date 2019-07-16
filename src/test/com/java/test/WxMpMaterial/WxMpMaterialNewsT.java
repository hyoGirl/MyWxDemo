package com.java.test.WxMpMaterial;

import com.alibaba.fastjson.JSON;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ashura1110
 * @ClassName XULEI
 * @description TODO
 * @Date 2019/7/7 11:16
 * @Version 1.0
 */
public class WxMpMaterialNewsT {


//    Articles	是	图文消息，一个图文消息支持1到8条图文
//    thumb_media_id	是	图文消息缩略图的media_id，可以在素材管理-新增素材中获得
//    author	否	图文消息的作者
//    title	是	图文消息的标题
//    content_source_url	否	在图文消息页面点击“阅读原文”后的页面，受安全限制，如需跳转Appstore，可以使用itun.es或appsto.re的短链服务，并在短链后增加 #wechat_redirect 后缀。
//    content	是	图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文。
//    digest	否	图文消息的描述，如本字段为空，则默认抓取正文前64个字
//    show_cover_pic	否	是否显示封面，1为显示，0为不显示
//    need_open_comment	否	Uint32 是否打开评论，0不打开，1打开
//    only_fans_can_comment	否	Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论

    public static void main(String[] args) {


        WxMpMaterialNews wxMpMaterialNews=new WxMpMaterialNews();


        WxMpMaterialNews.WxMpMaterialNewsArticle wxMpMaterialNewsArticle=new WxMpMaterialNews.WxMpMaterialNewsArticle();


        wxMpMaterialNewsArticle.setAuthor("xulei");
        wxMpMaterialNewsArticle.setContent("个人账号测试图文");
        wxMpMaterialNewsArticle.setContentSourceUrl("");
        wxMpMaterialNewsArticle.setDigest("个人账号测试图文");
        wxMpMaterialNewsArticle.setNeedOpenComment(false);
        wxMpMaterialNewsArticle.setOnlyFansCanComment(false);
        wxMpMaterialNewsArticle.setShowCoverPic(true);
        wxMpMaterialNewsArticle.setTitle("图文消息的标题");
        wxMpMaterialNewsArticle.setThumbMediaId("R8Qdal-8QUxbQoa_EFS-4unriToxzQeD-doHJCnoGQM");

        List<WxMpMaterialNews.WxMpMaterialNewsArticle> articles = new ArrayList<>();


        articles.add(wxMpMaterialNewsArticle);


        wxMpMaterialNews.setArticles(articles);

        wxMpMaterialNews.setCreateTime(new Date());

        wxMpMaterialNews.setUpdateTime(new Date());


        System.out.println(JSON.toJSON(wxMpMaterialNews));


    }





}

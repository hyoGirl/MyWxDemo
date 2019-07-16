package com.java.test;

import com.alibaba.fastjson.JSON;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: xulei
 * @Date: 2019/6/17 0017 15:02
 * @Description:
 */
public class MaterialNewsUpload {

    public static void main(String[] args) {


        WxMpMaterialNews news=new WxMpMaterialNews();

        Date createTime=new Date();
        Date updateTime=new Date();

        List<WxMpMaterialNews.WxMpMaterialNewsArticle> articles = new ArrayList<>();


        WxMpMaterialNews.WxMpMaterialNewsArticle article=new WxMpMaterialNews.WxMpMaterialNewsArticle();

        //图文消息的作者.
        article.setAuthor("zhang");

        //在图文消息页面点击“阅读原文”后的页面链接.
        article.setContentSourceUrl("");

        //是否打开评论，0不打开，1打开.
        article.setNeedOpenComment(true);
        //是否粉丝才可评论，0所有人可评论，1粉丝才可评论.
        article.setOnlyFansCanComment(true);
        //是否显示封面，true为显示，false为不显示.
        article.setShowCoverPic(true);

        // (必填) 图文消息缩略图的media_id，
        article.setThumbMediaId("FN4nzwEliWyMLidFxbOrYVtXfItq7gJLhzW38rE8c5o");

        //(必填) 图文消息的标题.
        article.setTitle("标题");

        //(必填) 图文消息页面的内容
        article.setContent("文章内容001");

        //图文消息的描述.
        article.setDigest("这个是第一个图文消息");


        articles.add(article);



        news.setArticles(articles);

        news.setCreateTime(createTime);

        news.setUpdateTime(updateTime);


        System.out.println(JSON.toJSON(news));

        Date createTime1=new Date();
        System.out.println(createTime1);
    }
}

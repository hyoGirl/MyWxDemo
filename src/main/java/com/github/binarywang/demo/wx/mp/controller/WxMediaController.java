package com.github.binarywang.demo.wx.mp.controller;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.demo.wx.mp.utils.MyFileUtils;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

/**
 * @Auther: ashur
 * @Date: 2019/6/12 0012 10:31
 * @Description: 素材管理的相关接口，包括媒体管理的接口
 */
@AllArgsConstructor
@Controller
@RequestMapping("/wx/media")
public class WxMediaController {


    @Autowired
    WxMpMaterialService wxMpMaterialService;

    /**
     * 新增临时素材
     *
     * @param mediaType
     * @param multipartFile
     * @return
     */

    @PostMapping("/upload")
    @ResponseBody
    public String mediaUpload(@RequestParam("mediaType") String mediaType,
                              @RequestParam("file") MultipartFile multipartFile) {
        WxMediaUploadResult wxMediaUploadResult = null;
        try {
            File file = MyFileUtils.transferFile(multipartFile);
            wxMediaUploadResult = wxMpMaterialService.mediaUpload(mediaType, file);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(wxMediaUploadResult);

    }

    /**
     * 新增非图文类型的永久素材
     * @param mediaType
     * @param name
     * @param multipartFile
     * @param videoTitle
     * @param videoIntroduction
     * @return
     */
    @PostMapping("/materialFileUpload")
    @ResponseBody
    public String materialFileUpload(@RequestParam("mediaType") String mediaType,
                              @RequestParam("name") String name,
                              @RequestParam("file") MultipartFile multipartFile,
                              @RequestParam("videoTitle") String videoTitle,
                              @RequestParam("videoIntroduction") String videoIntroduction) {
        WxMpMaterialUploadResult wxMpMaterialUploadResult = null;
        try {
            WxMpMaterial material=new WxMpMaterial();
            material.setName(name);
            File file = MyFileUtils.transferFile(multipartFile);
            material.setFile(file);
            material.setVideoIntroduction(videoIntroduction);
            material.setVideoTitle(videoTitle);
            wxMpMaterialUploadResult = wxMpMaterialService.materialFileUpload(mediaType, material);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(wxMpMaterialUploadResult);

    }


    /**
     * 获取临时素材
     *
     * @param mediaId
     * @param response
     */
    @PostMapping("/get")
    public void download(@RequestParam("mediaId") String mediaId, HttpServletResponse response) {
        try {
            File file = wxMpMaterialService.mediaDownload(mediaId);
            MyFileUtils.download(file, response);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * 新增非图文永久素材
     * 通过POST表单来调用接口，表单id为media，包含需要上传的素材内容，有filename、filelength、content-type等信息。请注意：图片素材将进入公众平台官网素材管理模块中的默认分组。
     * 新增永久视频素材需特别注意：
     * 在上传视频素材时需要POST另一个表单，id为description，包含素材的描述信息，内容格式为JSON，格式如下：
     * {   "title":VIDEO_TITLE,   "introduction":INTRODUCTION   }
     * 详情请见: <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738729&token=&lang=zh_CN">新增永久素材</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE
     *
     * 除了3天就会失效的临时素材外，开发者有时需要永久保存一些素材，届时就可以通过本接口新增永久素材。
     * 永久图片素材新增后，将带有URL返回给开发者，开发者可以在腾讯系域名内使用（腾讯系域名外使用，图片将被屏蔽）。
     * 请注意：
     * 1、新增的永久素材也可以在公众平台官网素材管理模块中看到
     * 2、永久素材的数量是有上限的，请谨慎新增。图文消息素材和图片素材的上限为5000，其他类型为1000
     * 3、素材的格式大小等要求与公众平台官网一致。具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式
     * 4、调用该接口需https协议
     * </pre>
     *
     */
    @GetMapping("/materialFileUpload2")
    @ResponseBody
    public  String materialFileUpload(@RequestParam("file") MultipartFile multipartFile,
                                      @RequestParam("mediaType")String mediaType,
                                      @RequestParam("name")String name,
                                      @RequestParam("videoTitle")String videoTitle,
                                      @RequestParam("videoIntroduction")String videoIntroduction
    ){
        WxMpMaterialUploadResult wxMpMaterialUploadResult =null;
        try {
            WxMpMaterial mpMaterial=new WxMpMaterial();
            File file = MyFileUtils.transferFile(multipartFile);
            mpMaterial.setFile(file);
            mpMaterial.setName(name);
            mpMaterial.setVideoIntroduction(videoIntroduction);
            mpMaterial.setVideoTitle(videoTitle);
            wxMpMaterialUploadResult = wxMpMaterialService.materialFileUpload(mediaType, mpMaterial);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(wxMpMaterialUploadResult);
    }


    /**
     * 传图文消息内的图片获取URL
     * @param mediaType
     * @param multipartFile
     * @return
     */

    @PostMapping("/uploadImage")
    @ResponseBody
    public  String mediaImgUpload(@RequestParam("mediaType") String mediaType,
                               @RequestParam("file") MultipartFile multipartFile){
        WxMediaImgUploadResult wxMediaImgUploadResult =null;
        try {
            File file = MyFileUtils.transferFile(multipartFile);
             wxMediaImgUploadResult = wxMpMaterialService.mediaImgUpload(file);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(wxMediaImgUploadResult);
    }


    /**
     * <pre>
     * 新增永久图文素材
     *
     * 详情请见: <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738729&token=&lang=zh_CN">新增永久素材</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN
     *
     * 除了3天就会失效的临时素材外，开发者有时需要永久保存一些素材，届时就可以通过本接口新增永久素材。
     * 永久图片素材新增后，将带有URL返回给开发者，开发者可以在腾讯系域名内使用（腾讯系域名外使用，图片将被屏蔽）。
     * 请注意：
     * 1、新增的永久素材也可以在公众平台官网素材管理模块中看到
     * 2、永久素材的数量是有上限的，请谨慎新增。图文消息素材和图片素材的上限为5000，其他类型为1000
     * 3、素材的格式大小等要求与公众平台官网一致。具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式
     * 4、调用该接口需https协议
     * </pre>
     *
     * @param news 上传的图文消息, 请看{@link WxMpMaterialNews}
     */
    @PostMapping("/materialNewsUpload")
    @ResponseBody
    public  String materialNewsUpload(@RequestBody WxMpMaterialNews news) throws WxErrorException {

//        {"mediaId":"R8Qdal-8QUxbQoa_EFS-4uhYitb3dZZm17zZGHox7pA"}
        news.setUpdateTime(new Date());
        news.setCreateTime(new Date());
        WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpMaterialService.materialNewsUpload(news);
        return JSON.toJSONString(wxMpMaterialUploadResult);
    }



    /**
     * 上传图文消息内的图片获取URL
     *
     * @param multipartFile
     */
    @PostMapping("/mediaImgUpload")
    @ResponseBody
    public String mediaImgUpload(@RequestParam("file") MultipartFile multipartFile) {

        String url = "";
        try {
            File file = MyFileUtils.transferFile(multipartFile);
            WxMediaImgUploadResult wxMediaImgUploadResult = wxMpMaterialService.mediaImgUpload(file);
            url = wxMediaImgUploadResult.getUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

}

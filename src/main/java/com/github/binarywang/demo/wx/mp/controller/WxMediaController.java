package com.github.binarywang.demo.wx.mp.controller;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.demo.wx.mp.utils.MyFileUtils;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import jdk.internal.util.xml.impl.Input;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.channels.FileChannel;

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
     * 新增图文永久素材
     * @param news
     * @return
     */
    @PostMapping("/materialNewsUpload")
    @ResponseBody
    public String materialNewsUpload(@RequestBody WxMpMaterialNews news) {
        WxMpMaterialUploadResult wxMpMaterialUploadResult=null;
        try {
             wxMpMaterialUploadResult = wxMpMaterialService.materialNewsUpload(news);
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
     * 获取永久素材
     *
     * @param mediaId
     *
     */
    @PostMapping("/materialImageOrVoiceDownload")
    public void materialImageOrVoiceDownload(@RequestParam("mediaId") String mediaId) {
        try {
            InputStream inputStream =  wxMpMaterialService.materialImageOrVoiceDownload(mediaId);
            File file=new File("e:/000.jpg");
            int len=0;
            byte[] buf=new byte[8192];
            FileOutputStream out=new FileOutputStream(file);

            while((len=inputStream.read(buf))!=-1){
                out.write(buf,0,len);
            }
            inputStream.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

package com.github.binarywang.demo.wx.mp.controller;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.demo.wx.mp.utils.MyFileUtils;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

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
     * @param mediaType
     * @param multipartFile
     * @return
     */

    @PostMapping("/upload")
    @ResponseBody
    public  String mediaUpload(@RequestParam("mediaType") String mediaType,
                               @RequestParam("file") MultipartFile multipartFile){
        WxMediaUploadResult wxMediaUploadResult=null;
        try {
            File file = MyFileUtils.transferFile(multipartFile);
            wxMediaUploadResult = wxMpMaterialService.mediaUpload(mediaType, file);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(wxMediaUploadResult);

    }

    /**
     * 获取临时素材
     * @param mediaId
     * @param response
     */
    @PostMapping("/get")
    public  void download(@RequestParam("mediaId") String mediaId,HttpServletResponse response){
        try {
            File file = wxMpMaterialService.mediaDownload(mediaId);
            MyFileUtils.download(file,response);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }





}

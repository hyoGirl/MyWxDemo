package com.github.binarywang.demo.wx.mp.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: ashur
 * @Date: 2019/6/12 0012 11:13
 * @Description:
 */
public class MyFileUtils {

    /**
     * MultipartFile 转换为普通的file
     */

    public static File transferFile(MultipartFile multipartFile) {


        String originalFilename = multipartFile.getOriginalFilename();
        String userPath = System.getProperty("user.dir") + "/upload";
        File filePath = new File(userPath);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        try {
            InputStream stream = new ByteArrayInputStream(multipartFile.getBytes());
            File file = new File(userPath + "/" + originalFilename);
            FileOutputStream outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buffer = new byte[8192];
            while ((len = stream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            stream.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void download(File file, HttpServletResponse response) {
        InputStream fis = null;
        BufferedOutputStream out = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(file));
            out = new BufferedOutputStream(response.getOutputStream());
            System.out.println(file.getName());
            response.setHeader("Content-disposition", "attachment; filename=" + new String(file.getName().getBytes(), "ISO8859-1"));
            int len = 0;
            byte[] buffer = new byte[8192];

            while ((len = fis.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

package com.github.binarywang.demo.wx.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@SpringBootApplication
@EnableAsync
public class WxMpDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(WxMpDemoApplication.class, args);
    }


}

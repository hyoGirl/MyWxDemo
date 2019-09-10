package com.github.binarywang.demo.wx.mp.presto;

/**
 * \* @Created with IntelliJ IDEA.
 * \* @author: baibing.shang
 * \* @Date: 2019/1/14
 * \* @Description:
 * \
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "presto")
@Data
public class PrestoConfig {
    private String url;

    private String username;

    private String password;

}
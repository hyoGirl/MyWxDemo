package com.github.binarywang.demo.wx.mp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
@Data
public class HikariPoolConfig {

    private Integer minimumIdle;

    private Integer maximumPoolSize;

    private String poolName;

    private Integer idleTimeout;

    private Integer connectionTimeout;

    private String connectionTestQuery;

    private Boolean autoCommit;

    private Integer maxLifetime;
}

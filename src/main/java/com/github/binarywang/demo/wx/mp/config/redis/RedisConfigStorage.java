package com.github.binarywang.demo.wx.mp.config.redis;

import com.github.binarywang.demo.wx.mp.config.RedisOperation;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.enums.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: xulei
 * @Date: 2019/7/15 0015 16:05
 * @Description:
 */

public class RedisConfigStorage extends WxMpInMemoryConfigStorage {


    private static final String ACCESS_TOKEN_KEY = "wx:access_token:";

    /**
     * 使用连接池保证线程安全.
     */


    private RedisOperation redisOperation;

    private String accessTokenKey;

    public RedisConfigStorage(RedisOperation redisOperation) {


        this.redisOperation = redisOperation;


    }

    /**
     * 每个公众号生成独有的存储key.
     */
    @Override
    public void setAppId(String appId) {
        super.setAppId(appId);
        this.accessTokenKey = ACCESS_TOKEN_KEY.concat(appId);
    }

    private String getTicketRedisKey(TicketType type) {
        return String.format("wx:ticket:key:%s:%s", this.appId, type.getCode());
    }

    @Override
    public String getAccessToken() {
       return redisOperation.get(this.accessTokenKey,String.class);

    }

    @Override
    public boolean isAccessTokenExpired() {
//        try (Jedis jedis = this.jedisPool.getResource()) {
//            return jedis.ttl(accessTokenKey) < 2;
//        }

        return redisOperation.getExpire(accessTokenKey)<2;
    }

    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
//        try (Jedis jedis = this.jedisPool.getResource()) {
//            jedis.setex(this.accessTokenKey, expiresInSeconds - 200, accessToken);
//        }

        redisOperation.expire(this.accessTokenKey, expiresInSeconds - 200,TimeUnit.SECONDS);
    }

    @Override
    public void expireAccessToken() {
//        try (Jedis jedis = this.jedisPool.getResource()) {
//            jedis.expire(this.accessTokenKey, 0);
//        }
        redisOperation.expire(this.accessTokenKey,0,TimeUnit.SECONDS);

    }

    @Override
    public String getTicket(TicketType type) {
//        try (Jedis jedis = this.jedisPool.getResource()) {
//            return jedis.get(this.getTicketRedisKey(type));
//        }

       return  redisOperation.get(this.getTicketRedisKey(type),String.class);
    }

    @Override
    public boolean isTicketExpired(TicketType type) {
//        try (Jedis jedis = this.jedisPool.getResource()) {
//            return jedis.ttl(this.getTicketRedisKey(type)) < 2;
//        }

        return redisOperation.getExpire(this.getTicketRedisKey(type))<2;
    }

    @Override
    public synchronized void updateTicket(TicketType type, String jsapiTicket, int expiresInSeconds) {
//        try (Jedis jedis = this.jedisPool.getResource()) {
//            jedis.setex(this.getTicketRedisKey(type), expiresInSeconds - 200, jsapiTicket);
//        }
//
        // TODO

    }

    @Override
    public void expireTicket(TicketType type) {
//        try (Jedis jedis = this.jedisPool.getResource()) {
//            jedis.expire(this.getTicketRedisKey(type), 0);
//        }

        redisOperation.expire(this.getTicketRedisKey(type),0,TimeUnit.SECONDS);

    }
}

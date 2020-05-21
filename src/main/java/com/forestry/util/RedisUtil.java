package com.forestry.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public void del(String key) {
        if (key != null) {
            redisTemplate.delete(key);
        }
    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public int set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return 0;
        }
        catch(Exception e) {
            CommonUtil.Logger(this.getClass()).error("redis set err: ", e);
            e.printStackTrace();
            return -1;
        }
    }

    public int setWithSecondExpire(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return 0;
        }
        catch(Exception e) {
            CommonUtil.Logger(this.getClass()).error("redis setWithSecondExpire err: ", e);
            e.printStackTrace();
            return -1;
        }
    }
}
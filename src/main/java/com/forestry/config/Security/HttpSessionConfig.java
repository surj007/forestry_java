package com.forestry.config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 24) //session超时时间，单位秒
public class HttpSessionConfig {
    // 这里有个小坑，如果服务器用的是云服务器，不加这个会报错
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    //session策略，这里配置的是Header方式（有提供Header，Cookie等方式）
    @Bean
    public HeaderHttpSessionIdResolver httpSessionStrategy() {
        return new HeaderHttpSessionIdResolver("x-auth-token");
    }
}

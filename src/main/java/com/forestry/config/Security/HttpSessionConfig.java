package com.forestry.config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

@Configuration
// session超时时间，单位秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 24)
public class HttpSessionConfig {
    /*
        springsession会自动配置redis-cli config set notify-keyspace-events Egx
        但云redis一般禁止client config配置，所以要在这配置ConfigureRedisAction.NO_OP，也就是让springsession不自动配置redis-cli config set notify-keyspace-events Egx
        如果需要这个key通知，可以在云redis管理后台配置（或者工单联系售后工程师帮忙配置）
    */
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    // session策略，这里配置的是Header方式（提供Header、cookie等方式，默认是cookie）
    @Bean
    public HeaderHttpSessionIdResolver httpSessionStrategy() {
        return new HeaderHttpSessionIdResolver("x-auth-token");
    }
}

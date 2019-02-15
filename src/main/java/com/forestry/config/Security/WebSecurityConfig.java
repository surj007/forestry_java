package com.forestry.config.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forestry.dto.CommonResDto;
import com.forestry.service.AuthService;
import com.forestry.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthService authService;
    @Autowired
    SecurityMetadataSource securityMetadataSource;
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    AuthAccessDeniedHandler authAccessDeniedHandler;
    @Autowired
    UnAuthHandler unAuthHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
            "/index.html",
            "/static/**",
            "/auth/getCode4RegAndResetPwd",
            "/auth/getCode4Login",
            "/auth/regUser",
            "/auth/forgetPwd"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(securityMetadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
        .and()
            .formLogin()
                .loginProcessingUrl("/auth/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req,
                                                        HttpServletResponse res,
                                                        Authentication auth) throws IOException {
                        res.setContentType("application/json;charset=utf-8");
                        CommonResDto commonResDto = CommonResDto.ok("login success", UserUtil.getUserInfo());
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = res.getWriter();
                        out.write(om.writeValueAsString(commonResDto));
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse res,
                                                        AuthenticationException e) throws IOException {
                        res.setContentType("application/json;charset=utf-8");
                        CommonResDto commonResDto = null;
                        if(e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                            commonResDto = CommonResDto.error("手机号或密码错误");
                        }
                        else {
                            commonResDto = CommonResDto.error("login failed", e);
                        }
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = res.getWriter();
                        out.write(om.writeValueAsString(commonResDto));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
        .and()
            .logout()
                .permitAll()
                .logoutUrl("/auth/logout")
        .and()
            .csrf().disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unAuthHandler)
                    .accessDeniedHandler(authAccessDeniedHandler)
        .and()
            .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false);
    }
}

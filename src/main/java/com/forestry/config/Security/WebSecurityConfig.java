package com.forestry.config.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.forestry.util.UserUtil;
import com.forestry.dto.CommonResDto;
import com.forestry.service.AuthService;

@Component
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthService authService;
    @Autowired
    private SecurityMetadataSource securityMetadataSource;
    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    private AuthAccessDeniedHandler authAccessDeniedHandler;
    @Autowired
    private UnAuthHandler unAuthHandler;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // 使用authService（需要实现UserDetailsService接口）中的loadUserByUsername方法，找到数据库中user信息与请求中的password对比
        // 返回的user对象实现了UserDetails接口
        authenticationManagerBuilder.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(
            "/index.html",
            "/static/**",
            "/auth/getCode4RegAndResetPwd",
            "/auth/getCode4Login",
            "/auth/regUser",
            "/auth/forgetPwd"
        );
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()
            // 权限判断
            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                @Override
                // 泛型方法的写法
                public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                    // 获取当前路径所需角色
                    o.setSecurityMetadataSource(securityMetadataSource);
                    // 判断能否访问当前路径
                    o.setAccessDecisionManager(urlAccessDecisionManager);
                    return o;
                }
            })
            .and()
                .formLogin()
                    .loginProcessingUrl("/auth/login")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(
                            HttpServletRequest req,
                            HttpServletResponse res,
                            Authentication auth
                        ) throws IOException {
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
                        public void onAuthenticationFailure(
                            HttpServletRequest req,
                            HttpServletResponse res,
                            AuthenticationException e
                        ) throws IOException {
                            res.setContentType("application/json;charset=utf-8");
                            CommonResDto commonResDto = null;
                            if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
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
                    // logout后不再重定向，返回json
                    .logoutSuccessHandler(customLogoutSuccessHandler)
            .and()
                .csrf().disable()
            .exceptionHandling()
                // 未登录handler
                .authenticationEntryPoint(unAuthHandler)
                // 没权限handler
                .accessDeniedHandler(authAccessDeniedHandler)
            .and()
                .sessionManagement()
                    // 最多几个用户登录
                    .maximumSessions(1)
                    // 当达到最大值时，是否保留已经登录的用户
                    .maxSessionsPreventsLogin(false);

        // 配置login可以使用json类型
        // httpSecurity.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // 配置login接口传递多个参数：username、password、loginType
        // MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        // httpSecurity.authenticationProvider(myAuthenticationProvider)
        //     .addFilterAfter(myAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // 配置login可以使用json类型（也能使用x-www类型）
    // @Bean
    // CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
    //     CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
    //     customAuthenticationFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
    //         @Override
    //         public void onAuthenticationSuccess(
    //             HttpServletRequest req,
    //             HttpServletResponse res,
    //             Authentication auth
    //         ) throws IOException {
    //             res.setContentType("application/json;charset=utf-8");
    //             CommonResDto commonResDto = CommonResDto.ok("login success", UserUtil.getUserInfo());
    //             ObjectMapper om = new ObjectMapper();
    //             PrintWriter out = res.getWriter();
    //             out.write(om.writeValueAsString(commonResDto));
    //             out.flush();
    //             out.close();
    //         }
    //     });
    //     customAuthenticationFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
    //         @Override
    //         public void onAuthenticationFailure(
    //             HttpServletRequest req,
    //             HttpServletResponse res,
    //             AuthenticationException e
    //         ) throws IOException {
    //             res.setContentType("application/json;charset=utf-8");
    //             CommonResDto commonResDto = null;
    //             if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
    //                 commonResDto = CommonResDto.error("手机号或密码错误");
    //             }
    //             else {
    //                 commonResDto = CommonResDto.error("login failed", e);
    //             }
    //             ObjectMapper om = new ObjectMapper();
    //             PrintWriter out = res.getWriter();
    //             out.write(om.writeValueAsString(commonResDto));
    //             out.flush();
    //             out.close();
    //         }
    //     });
    //     customAuthenticationFilter.setFilterProcessesUrl("/auth/login");
    //     // authenticationManagerBean是父类中的方法
    //     customAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
    //     return customAuthenticationFilter;
    // }

    // 配置login接口传递多个参数：username、password、loginType（验证码、密码）
    // @Bean
    // MyAuthenticationProcessingFilter myAuthenticationProcessingFilter() throws Exception {
    //     MyAuthenticationProcessingFilter myAuthenticationProcessingFilter = new MyAuthenticationProcessingFilter();
    //     myAuthenticationProcessingFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
    //         @Override
    //         public void onAuthenticationSuccess(
    //             HttpServletRequest req,
    //             HttpServletResponse res,
    //             Authentication auth
    //         ) throws IOException {
    //             res.setContentType("application/json;charset=utf-8");
    //             CommonResDto commonResDto = CommonResDto.ok("login success", UserUtil.getUserInfo());
    //             ObjectMapper om = new ObjectMapper();
    //             PrintWriter out = res.getWriter();
    //             out.write(om.writeValueAsString(commonResDto));
    //             out.flush();
    //             out.close();
    //         }
    //     });
    //     myAuthenticationProcessingFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
    //         @Override
    //         public void onAuthenticationFailure(
    //             HttpServletRequest req,
    //             HttpServletResponse res,
    //             AuthenticationException e
    //         ) throws IOException {
    //             res.setContentType("application/json;charset=utf-8");
    //             CommonResDto commonResDto = null;
    //             if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
    //                 commonResDto = CommonResDto.error("手机号或密码错误");
    //             }
    //             else {
    //                 commonResDto = CommonResDto.error("login failed", e);
    //             }
    //             ObjectMapper om = new ObjectMapper();
    //             PrintWriter out = res.getWriter();
    //             out.write(om.writeValueAsString(commonResDto));
    //             out.flush();
    //             out.close();
    //         }
    //     });
    //     myAuthenticationProcessingFilter.setFilterProcessesUrl("/auth/login");
    //     myAuthenticationProcessingFilter.setAuthenticationManager(authenticationManagerBean());
    //     return myAuthenticationProcessingFilter;
    // }
}

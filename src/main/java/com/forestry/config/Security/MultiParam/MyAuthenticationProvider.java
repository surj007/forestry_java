package com.forestry.config.Security.MultiParam;

import com.zhengqing.config.security.filter.MyAuthenticationToken;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
 
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// 自定义用户名、密码校验
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MyAuthenticationToken myAuthenticationToken = (MyAuthenticationToken)authentication;
        String loginType = MapUtils.getString(myAuthenticationToken.getParam(), "loginType", "-1");
        String userName = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
 
        UserDetails userDetails = authService.loadUserByUsername(userName);
 
        // 自定义密码校验逻辑
        boolean isValid = true;
        
        if (!isValid) {
            throw new BadCredentialsException("密码错误");
        }
 
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
 
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
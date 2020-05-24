
package com.forestry.config.Security.MultiParam;
 
import com.alibaba.fastjson.JSONObject;
import com.zhengqing.utils.MultiReadHttpServletRequest;
import com.zhengqing.modules.system.entity.User;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

// 自定义请求传递参数
@Component
public class MyAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        if (
            !req.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) && 
            !req.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
        ) {
            throw new AuthenticationServiceException("请求头类型不支持: " + req.getContentType());
        }
 
        MyAuthenticationToken myAuthenticationToken;

        try {
            MultiReadHttpServletRequest multiReadHttpServletRequest = new MultiReadHttpServletRequest(req);
            // 将前端传递的数据转换成jsonBean数据格式
            User user = JSONObject.parseObject(multiReadHttpServletRequest.getBodyJsonStrByJson(multiReadHttpServletRequest), User.class);
            String loginType = user.getLoginType();
            Map<String, Object> map = new HashMap<>();
            map.put("loginType", loginType);
            myAuthenticationToken = new MyAuthenticationToken(user.getUsername(), user.getPassword(), null, map);
            myAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(multiReadHttpServletRequest));
        } 
        catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }

        Authentication authentication = this.getAuthenticationManager().authenticate(myAuthenticationToken);

        return authentication;
    }
}
package com.forestry.config.Security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import java.util.Collection;

@Component
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    // object为当前请求对象
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        String[] roles = {"ROLE_admin", "ROLE_employee"};

        // 可访问的角色
        return SecurityConfig.createList(roles);
    }

    // getAllConfigAttributes方法如果返回了所有定义的权限资源，Spring Security会在启动时校验每个ConfigAttribute是否配置正确，不需要校验直接返回null
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    // 当前FilterInvocationSecurityMetadataSource是否支持此aClass，也就是上面的Object object是否会传入aClass类型的对象
    // 这样写表示上面的Object object只能是FilterInvocation类型的
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}

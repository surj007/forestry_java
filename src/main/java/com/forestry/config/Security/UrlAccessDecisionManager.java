package com.forestry.config.Security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes){
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            String needRole = configAttribute.getAttribute();

            // authentication.getAuthorities是在User bean中重写的
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("当前角色不能访问此资源");
    }

    // 当前AccessDecisionManager是否支持此configAttribute，也就是上面的Collection<ConfigAttribute> configAttributes中是否会包含此configAttribute
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    // 当前AccessDecisionManager是否支持此clazz，也就是上面的Object object是否会传入clazz类型的对象
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

package com.forestry.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.forestry.bean.User;

public class UserUtil {
    // 获取session中的user信息
    public static User getUserInfo() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

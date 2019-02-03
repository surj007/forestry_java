package com.forestry.util;

import com.forestry.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static User getUserInfo() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

package com.forestry.controller;

import com.forestry.bean.User;
import com.forestry.dto.CommonResDto;
import com.forestry.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public CommonResDto regUser(@RequestBody User user) {
        int result = authService.regUser(user.getUsername(), user.getPassword());
        if (result == 1) {
            return CommonResDto.ok("reg success");
        }
        else if (result == -1) {
            return CommonResDto.error("用户名已存在，请重新输入");
        }
        return CommonResDto.error("reg failed");
    }
}

package com.forestry.controller;

import com.forestry.dto.CommonResDto;
import com.forestry.service.AuthService;
import com.forestry.util.CommonUtil;
import com.forestry.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "/getCode", method = RequestMethod.GET)
    public CommonResDto getCode(HttpServletResponse res, String phone) {
        if(phone == null) {
            res.setStatus(400);
            return CommonResDto.error("缺少参数");
        }

        if(redisUtil.setWithExpire(phone, CommonUtil.generateCode(), 300) == 0) {
            return CommonResDto.ok("get code success");
        }
        else {
            return CommonResDto.error("get code failed");
        }
    }

    @RequestMapping(value = "/regUser", method = RequestMethod.POST)
    public CommonResDto regUser(HttpServletResponse res, @RequestBody Map<String,Object> reqMap) {
        if(reqMap.get("phone") == null ||
           reqMap.get("code") == null ||
           reqMap.get("username") == null ||
           reqMap.get("password") == null) {
            res.setStatus(404);
            return CommonResDto.error("缺少参数");
        }

        int codeResult = authService.checkCode(reqMap.get("phone").toString(), reqMap.get("code").toString());

        if(codeResult == 1) {
            return CommonResDto.error("短信验证码已过期，请重新获取");
        }
        else if(codeResult == 2) {
            return CommonResDto.error("短信验证码输入错误，请重新输入");
        }

        int result = authService.regUser(reqMap.get("username").toString(), reqMap.get("password").toString(), reqMap.get("phone").toString());

        if (result == 1) {
            return CommonResDto.ok("reg user success");
        }
        else if (result == -1) {
            return CommonResDto.error("用户名已存在，请重新输入");
        }
        return CommonResDto.error("reg user failed");
    }
}

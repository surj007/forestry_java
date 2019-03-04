package com.forestry.controller;

import com.forestry.dto.CommonResDto;
import com.forestry.service.AuthService;
import com.forestry.service.SmsService;
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
    @Autowired
    SmsService smsService;

    @RequestMapping(value = "/getCode4RegAndResetPwd", method = RequestMethod.GET)
    public CommonResDto getCode4Reg(HttpServletResponse res, String phone, String type) {
        if(phone == null||
           type == null) {
            res.setStatus(400);

            return CommonResDto.error("缺少参数");
        }

        if(type.equals("resetPwd")) {
            if(!authService.isReg(phone)) {
                return CommonResDto.error("当前手机号未注册");
            }
        }

        String code = CommonUtil.generateCode();

        if(redisUtil.setWithExpire(phone, code, 300) == 0) {
            try {
                //smsService.sendSms(phone, code);
                return CommonResDto.ok("getCode4Reg success");
            }
            catch(Exception e) {
                CommonUtil.Logger(this.getClass()).error("send sms err: ", e);
                return CommonResDto.error("sendCode4Reg failed");
            }
        }
        else {
            return CommonResDto.error("getCode4Reg failed");
        }
    }

    @RequestMapping(value = "/getCode4Login", method = RequestMethod.GET)
    public CommonResDto getCode4Login(HttpServletResponse res, String phone) {
        if(phone == null) {
            res.setStatus(400);

            return CommonResDto.error("缺少参数");
        }

        String code = CommonUtil.generateCode();
System.out.println(code);
        int result = authService.setCode4Login(phone, code);

        if(result == 0) {
            return CommonResDto.error("当前手机号未注册");
        }
        else {
            try {
                //smsService.sendSms(phone, code);
                return CommonResDto.ok("getCode4Login success");
            }
            catch(Exception e) {
                CommonUtil.Logger(this.getClass()).error("send sms err: ", e);
                return CommonResDto.error("发送验证码失败，请稍后重试");
            }
        }
    }

    @RequestMapping(value = "/regUser", method = RequestMethod.POST)
    public CommonResDto regUser(HttpServletResponse res, @RequestBody Map<String, Object> reqMap) {
        if(reqMap.get("username") == null ||
           reqMap.get("code") == null ||
           reqMap.get("password") == null) {
            res.setStatus(400);

            return CommonResDto.error("缺少参数");
        }

        int codeResult = authService.checkCode(reqMap.get("username").toString(), reqMap.get("code").toString());

        if(codeResult == 1) {
            return CommonResDto.error("短信验证码已过期，请重新获取");
        }
        else if(codeResult == 2) {
            return CommonResDto.error("短信验证码输入错误，请重新输入");
        }

        int result4RegUser = authService.regUser(reqMap.get("username").toString(), reqMap.get("password").toString());

        if(result4RegUser == 1) {
            int result4AddRole = authService.addRole(reqMap.get("username").toString(), 1);

            if(result4AddRole == 1) {
                return CommonResDto.ok("regUser success");
            }
        }
        else if (result4RegUser == -1) {
            return CommonResDto.error("手机号已存在，请重新输入或登陆");
        }

        return CommonResDto.error("regUser failed");
    }

    @RequestMapping(value = "/forgetPwd", method = RequestMethod.POST)
    public CommonResDto resetPwd(HttpServletResponse res, @RequestBody Map<String,Object> reqMap) {
        if(reqMap.get("username") == null ||
           reqMap.get("code") == null ||
           reqMap.get("password") == null) {
            res.setStatus(400);

            return CommonResDto.error("缺少参数");
        }

        int codeResult = authService.checkCode(reqMap.get("username").toString(), reqMap.get("code").toString());

        if(codeResult == 1) {
            return CommonResDto.error("短信验证码已过期，请重新获取");
        }
        else if(codeResult == 2) {
            return CommonResDto.error("短信验证码输入错误，请重新输入");
        }

        int result = authService.updateUser(reqMap.get("username").toString(), reqMap.get("password").toString());

        if(result == -1) {
            return CommonResDto.error("当前手机号未注册");
        }
        else if(result == 1) {
            return CommonResDto.ok("resetPwd success");
        }

        return CommonResDto.error("resetPwd failed");
    }
}

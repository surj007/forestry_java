package com.forestry.controller;

import com.forestry.bean.User;
import com.forestry.dto.CommonResDto;
import com.forestry.service.EmployeeService;
import com.forestry.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
    public CommonResDto getEmployee() {
        List<User> employeeList = employeeService.getEmployee();

        return CommonResDto.ok("getEmployee success", employeeList);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public CommonResDto editCompany(HttpServletResponse res, @RequestBody Map<String, Object> reqMap) {
        ArrayList<User> userList = CommonUtil.formatReqMapItem2ArrayList(reqMap.get("employee"), User.class);
        for(User user : userList) {
            if(user.getName() == null || user.getUsername() == null || user.getSocialSecurityPic() == null || user.getCardFrontPic() == null || user.getCardOppositePic() == null) {
                res.setStatus(400);
                return CommonResDto.error("缺少参数");
            }
        }

        int result = employeeService.addEmployeeList(userList);
        if(result == -1) {
            return CommonResDto.error("业务员姓名或手机号已存在，请重新提交");
        }
        else if(result == -2) {
            return CommonResDto.error("添加业务员失败，请重新提交");
        }
        else {
            return CommonResDto.ok("addEmployee success");
        }
    }
}

package com.forestry.controller;

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

import com.forestry.bean.User;
import com.forestry.dto.CommonResDto;
import com.forestry.service.EmployeeService;
import com.forestry.util.CommonUtil;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
    public CommonResDto getEmployee() {
        List<User> employeeList = employeeService.getEmployee();

        return CommonResDto.ok("getEmployee success", employeeList);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    // 这块employee参数是个数组，可以按当前方法处理
    /*
        也可以直接封装dto接收数组
        @Data
        public class EmployeeDTO {
            private User[] employee;
            // private List<User> employee;
        }
        @RequestBody EmployeeDTO employeeDTO
        User[] users = employeeDTO.getEmployee();
        //  List<User> users = employeeDTO.getEmployee();

    */
    /* 
        或者
        @RequestBody JSONObject jsonObject
        JSONArray jsonArray = jsonObject.getJSONArray("employee");
        List<User> userList = (List)JSONArray.toCollection(jsonArray, User.class);
    */
    public CommonResDto addEmployee(HttpServletResponse res, @RequestBody Map<String, Object> reqMap) {
        ArrayList<User> userList = CommonUtil.formatReqMapItem2ArrayList(reqMap.get("employee"), User.class);
        
        for (User user : userList) {
            if (
                user.getName() == null || 
                user.getUsername() == null || 
                user.getSocialSecurityPic() == null || 
                user.getCardFrontPic() == null || 
                user.getCardOppositePic() == null
            ) {
                res.setStatus(400);
                return CommonResDto.error("缺少参数");
            }
        }

        int result = employeeService.addEmployeeList(userList);

        if (result == -1) {
            return CommonResDto.error("业务员手机号已存在，请重新提交");
        }
        else if (result == -2) {
            return CommonResDto.error("添加业务员失败，请重新提交");
        }
        else {
            return CommonResDto.ok("addEmployee success");
        }
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @RequestMapping(value = "/editEmployee", method = RequestMethod.POST)
    public CommonResDto editEmployee(HttpServletResponse res, @RequestBody Map<String, Object> reqMap) {
        ArrayList<User> userList = CommonUtil.formatReqMapItem2ArrayList(reqMap.get("employee"), User.class);
        
        for (User user : userList) {
            if (
                user.getName() == null || 
                user.getUsername() == null || 
                user.getSocialSecurityPic() == null || 
                user.getCardFrontPic() == null || 
                user.getCardOppositePic() == null
            ) {
                res.setStatus(400);
                return CommonResDto.error("缺少参数");
            }
        }

        int result = employeeService.editEmployeeList(userList);

        if (result == -1) {
            return CommonResDto.error("业务员手机号已存在，请重新提交");
        }
        else if (result == -2) {
            return CommonResDto.error("修改业务员失败，请重新提交");
        }
        else {
            int changeStatusResult = employeeService.changeCompanyStatus();

            return CommonResDto.ok("editEmployee success");
        }
    }
}

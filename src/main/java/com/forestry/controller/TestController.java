package com.forestry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.forestry.bean.Test;
import com.forestry.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    /*
       get请求获取参数：
       1、请求参数字段作为形参：getData(Sring username) {}
       2、需要重命名字段或需要对字段校验的时候：getData(@RequestParam(name = "name", require = "true") String username) {}
       3、字段较多时，直接用bean接收：getData(User user) {}
    */
    /*
       post请求获取参数
       {
        类型为x-www-form-urlencoded
        {
            请求参数字段作为形参：postData(Sring username) {}
            需要重命名字段或需要对字段校验的时候：postData(@RequestParam(name = "name", require = "true") String username) {}
            字段较多或需要校验参数时，直接用bean接收，postData(@Validated User user) {}
        }
        
        类型为json时
        {
            postData(@RequestBody @Validated User user) {}
            postData(@RequestBody Map<String,Object> reqMap) {}
        }

        json中含有数组参数：见EmployeeController.addEmployee
       }
    */

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public List<Test> getData() {
        return testService.getData();
    }
}

package com.forestry.controller;

import com.forestry.bean.Test;
import com.forestry.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    /*
       get请求获取参数：直接用@RequestParam校验
       1、请求参数字段作为形参，(Sring username)
       2、需要重命名字段或者对字段有要求的，(@RequestParam(...) String username)
       3、字段较多时，直接用bean接收，(User user) user.getUserName()
    */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public List<Test> getData() {
        return testService.getData();
    }

    /*
       post请求获取参数：
       1、类型为x-www-form-urlencoded时，请求参数字段作为形参，(Sring username)，可以用@RequestParam校验
       2、类型为x-www-form-urlencoded时，直接用bean接收，(User user) user.getUserName()，可以使用@Validated
       3、类型为json时，需要加@RequestBody，(@RequestBody User user)，可以使用@Validated
       4、类型为json时，不定义实体类，(@RequestBody Map<String,Object> reqMap)，自己校验？
       5、发送过来的数据中含有对象数组时，可以重新定义实体类，在实体类中添加list。
       或者使用fastjson转换一下，先转换成jsonsrray，再转换成arraylist。
    */
}

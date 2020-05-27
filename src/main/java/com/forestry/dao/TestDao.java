package com.forestry.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.forestry.bean.Test;

public interface TestDao {
    // @Param能够给参数重命名，使用了@Param以后mapper.xml中需要javaBean.xxx获取bean中参数
    public List<Test> getData();
}

package com.forestry.dao;

import com.forestry.bean.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestDao {
    //@Param能够给参数命名，使用@param，要#{xxx.xxx}
    List<Test> getData();
}

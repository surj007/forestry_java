package com.forestry.dao;

import com.forestry.bean.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestDao {
    List<Test> getData();
}

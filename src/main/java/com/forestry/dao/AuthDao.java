package com.forestry.dao;

import com.forestry.bean.Role;
import com.forestry.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthDao {
    User loadUserByUsername(@Param("username") String username);

    List<Role> getRolesByUserId(int id);

    int regUser(@Param("username") String username, @Param("password") String password, @Param("phone") String phone);
}

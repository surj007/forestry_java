package com.forestry.dao;

import com.forestry.bean.Role;
import com.forestry.bean.User;

import java.util.List;

public interface AuthDao {
    User loadUserByUsername(String username);

    List<Role> getRolesByUserId(int id);

    int regUser(String username, String password);

    int updateCode(String username, String code);

    int updateUser(String username, String password);

    int addRole(int uid, int rid);
}

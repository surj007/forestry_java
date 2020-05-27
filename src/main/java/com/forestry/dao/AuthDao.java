package com.forestry.dao;

import java.util.List;

import com.forestry.bean.Role;
import com.forestry.bean.User;

public interface AuthDao {
    public User loadUserByUsername(String username);

    public List<Role> getRolesByUserId(int id);

    public int regUser(String username, String password);

    public int updateCode(String username, String code);

    public int updateUser(String username, String password);

    public int addRole(int uid, int rid);

    public int delRole(int uid);
}

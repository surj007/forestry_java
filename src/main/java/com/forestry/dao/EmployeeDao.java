package com.forestry.dao;

import com.forestry.bean.User;

import java.util.List;

public interface EmployeeDao {
    List<User> getEmployee(int userId);

    User getEmployeeByNameOrUsername(String name, String username);

    int addEmployee(User user);

    int relatedCompanyAndEmployee(int uid, int userId);

    int updateEmployee(User user);
}

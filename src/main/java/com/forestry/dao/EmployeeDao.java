package com.forestry.dao;

import com.forestry.bean.User;

import java.util.List;

public interface EmployeeDao {
    List<User> getEmployee(int userId);

    int addEmployee(User user);

    int relatedCompanyAndEmployee(int uid, int userId);

    int delEmployee(int id);

    int delRelateCompanyAndEmployee(int uid);
}

package com.forestry.dao;

import java.util.List;

import com.forestry.bean.User;

public interface EmployeeDao {
    public List<User> getEmployee(int userId);

    public int addEmployee(User user);

    public int relatedCompanyAndEmployee(int uid, int userId);

    public int delEmployee(int id);

    public int delRelateCompanyAndEmployee(int uid);

    public int changeCompanyStatus(int uid);
}

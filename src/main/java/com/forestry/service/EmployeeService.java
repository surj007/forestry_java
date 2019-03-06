package com.forestry.service;

import com.forestry.bean.User;
import com.forestry.dao.AuthDao;
import com.forestry.dao.EmployeeDao;
import com.forestry.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    AuthDao authDao;

    public List<User> getEmployee() {
        return employeeDao.getEmployee(UserUtil.getUserInfo().getId());
    }

    public User getEmployeeByNameOrUsername(String name, String username) {
        return employeeDao.getEmployeeByNameOrUsername(name, username);
    }

    public int addEmployee(User user) {
        user.setBoss(UserUtil.getUserInfo().getId());
        return employeeDao.addEmployee(user);
    }

    public int relatedCompanyAndEmployee(int uid) {
        return employeeDao.relatedCompanyAndEmployee(uid, UserUtil.getUserInfo().getId());
    }

    public int updateEmployee(User user) {
        return employeeDao.updateEmployee(user);
    }

    public int addEmployeeList(ArrayList<User> userList) {
        int employeeRoleId = 2;
        for(User user : userList) {
            if(this.getEmployeeByNameOrUsername(user.getName(), user.getUsername()) != null) {
                return -1;
            }

            if(this.addEmployee(user) != 1) {
                return -2;
            }
            if(this.relatedCompanyAndEmployee(user.getId()) != 1) {
                return -2;
            }
            if(authDao.addRole(user.getId(), employeeRoleId) != 1) {
                return -2;
            }
        }

        return 0;
    }
}

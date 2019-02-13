package com.forestry.service;

import com.forestry.bean.Company;
import com.forestry.dao.CompanyDao;
import com.forestry.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    CompanyDao companyDao;

    public Company getCompanyByUserId() {
        return companyDao.getCompanyByUserId(UserUtil.getUserInfo().getId());
    }

    // TODO 关联user
    public int addCompany(Company company) {
        return companyDao.addCompany(company);
    }

    public int updateCompany(Company company) {
        return companyDao.updateCompany(company);
    }
}

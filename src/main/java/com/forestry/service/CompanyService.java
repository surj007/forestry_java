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

    public Company getCompanyByNameOrCode(String name, String code) {
        return companyDao.getCompanyByNameOrCode(name, code);
    }

    public int addCompany(Company company) {
        return companyDao.addCompany(company);
    }

    public int relatedCompanyAndUser(int companyId) {
        return companyDao.relatedCompanyAndUser(UserUtil.getUserInfo().getId(), companyId);
    }

    public int updateCompany(Company company) {
        return companyDao.updateCompany(company);
    }
}

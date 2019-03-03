package com.forestry.dao;

import com.forestry.bean.Company;

public interface CompanyDao {
    Company getCompanyByUserId(int userId);

    Company getCompanyByNameOrCode(String name, String code);

    int addCompany(Company company);

    int relatedCompanyAndUser(int uid, int cid);

    int updateCompany(Company company);
}

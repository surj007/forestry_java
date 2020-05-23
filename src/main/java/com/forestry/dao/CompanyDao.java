package com.forestry.dao;

import com.forestry.bean.Company;

public interface CompanyDao {
    public Company getCompanyByUserId(int userId);

    public Company getCompanyByNameOrCode(String name, String code);

    public int addCompany(Company company);

    public int relatedCompanyAndUser(int uid, int cid);

    public int updateCompany(Company company);
}

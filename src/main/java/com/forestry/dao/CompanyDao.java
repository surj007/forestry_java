package com.forestry.dao;

import com.forestry.bean.Company;
import org.apache.ibatis.annotations.Param;

public interface CompanyDao {
    Company getCompanyByUserId(@Param("userId") int userId);

    int addCompany(@Param("company") Company company);

    int updateCompany(@Param("company") Company company);
}

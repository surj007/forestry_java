package com.forestry.controller;

import com.forestry.bean.Company;
import com.forestry.dto.CommonResDto;
import com.forestry.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/getCompany", method = RequestMethod.GET)
    public CommonResDto getCompany() {
        Company company = companyService.getCompanyByUserId();

        return CommonResDto.ok("getCompany success", company);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @RequestMapping(value = "/editCompany", method = RequestMethod.POST)
    public CommonResDto editCompany(@RequestBody @Valid Company company, BindingResult bindingResult) {
        if(company.getId() == 0) {
            if(companyService.addCompany(company) == 1) {
                if(companyService.relatedCompanyAndUser(company.getId()) == 1) {
                    return CommonResDto.ok("addCompany success");
                }
            }

            return CommonResDto.error("提交企业信息失败，请重新提交");
        }

        if(companyService.updateCompany(company) == 1) {
            return CommonResDto.ok("updateCompany success");
        }

        return CommonResDto.error("修改企业信息失败，请重新提交");
    }
}

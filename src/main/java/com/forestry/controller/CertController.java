package com.forestry.controller;

import com.forestry.bean.BoardCert;
import com.forestry.bean.WoodCert;
import com.forestry.dto.CommonResDto;
import com.forestry.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cert")
public class CertController {
    @Autowired
    CertService certService;

    @RequestMapping(value = "/addWoodCert", method = RequestMethod.POST)
    public CommonResDto addWoodCert(@RequestBody @Valid WoodCert woodCert, BindingResult bindingResult) {
        if(certService.addWoodCert(woodCert) == 1) {
            return CommonResDto.ok("addWoodCert success");
        }

        return CommonResDto.error("添加原木类开证单失败，请重新提交");
    }

    @RequestMapping(value = "/addBoardCert", method = RequestMethod.POST)
    public CommonResDto addBoardCert(@RequestBody @Valid BoardCert boardCert, BindingResult bindingResult) {
        if(certService.addBoardCert(boardCert) == 1) {
            return CommonResDto.ok("addBoardCert success");
        }

        return CommonResDto.error("添加板材类开证单失败，请重新提交");
    }
}

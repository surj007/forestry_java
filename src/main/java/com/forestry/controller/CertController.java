package com.forestry.controller;

import com.forestry.bean.BoardCert;
import com.forestry.bean.WoodCert;
import com.forestry.dto.CommonResDto;
import com.forestry.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    @RequestMapping(value = "/getCertAmount", method = RequestMethod.GET)
    public CommonResDto getCertAmount() throws ExecutionException, InterruptedException {
        Map<String, Object> resMap = new HashMap<>();
        List<Future<Integer>> futureList = new ArrayList<>();
        String[] keyArray = {"boardCertAmount", "woodCertAmount"};
        int i = 0;

        futureList.add(certService.getBoardCertAmount());
        futureList.add(certService.getWoodCertAmount());

        for (Future future : futureList) {
            resMap.put(keyArray[i++], future.get());
        }

        return CommonResDto.ok("getCertAmount success", resMap);
    }

    @RequestMapping(value = "/getCert", method = RequestMethod.GET)
    public CommonResDto getCert(@RequestParam(name = "type") String type, @RequestParam(name = "status") int status) throws ExecutionException, InterruptedException {
        Map<String, Object> resMap = new HashMap<>();
        List<Future> futureList = new ArrayList<>();
        String[] keyArray = new String[3];
        int i = 0;

        switch (type) {
            case "": {
                keyArray[0] = "boardCert";
                keyArray[1] = "woodCert";
                futureList.add(certService.getBoardCert(status));
                futureList.add(certService.getWoodCert(status));

                break;
            }
            case "wood": {
                keyArray[0] = "woodCert";
                futureList.add(certService.getWoodCert(status));

                break;
            }
            case "board": {
                keyArray[0] = "boardCert";
                futureList.add(certService.getBoardCert(status));

                break;
            }
            case "plant": {
                break;
            }
            default: {
                break;
            }
        }

        for (Future future : futureList) {
            resMap.put(keyArray[i++], future.get());
        }

        return CommonResDto.ok("getCert success", resMap);
    }
}

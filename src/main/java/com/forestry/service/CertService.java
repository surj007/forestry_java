package com.forestry.service;

import com.forestry.bean.BoardCert;
import com.forestry.bean.PlantCert;
import com.forestry.bean.WoodCert;
import com.forestry.dao.CertDao;
import com.forestry.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class CertService {
    @Autowired
    private CertDao certDao;

    public int addWoodCert(WoodCert woodCert) {
        return certDao.addWoodCert(woodCert, UserUtil.getUserInfo().getId());
    }

    public int addPlantCert(PlantCert plantCert) {
        return certDao.addPlantCert(plantCert, UserUtil.getUserInfo().getId());
    }

    public int addBoardCert(BoardCert boardCert) {
        return certDao.addBoardCert(boardCert, UserUtil.getUserInfo().getId());
    }

    public int addPlantCertPicture(String id, String picture_url, String picture_location, String picture_time) {
        return certDao.addPlantCertPicture(id, picture_url, picture_location, picture_time, UserUtil.getUserInfo().getId());
    }

    @Async
    public Future<Integer> getBoardCertAmount() {
        int boardCertAmount = certDao.getBoardCertAmount(UserUtil.getUserInfo().getId());
        return new AsyncResult<>(boardCertAmount);
    }

    @Async
    public Future<Integer> getWoodCertAmount() {
        int woodCertAmount = certDao.getWoodCertAmount(UserUtil.getUserInfo().getId());
        return new AsyncResult<>(woodCertAmount);
    }

    @Async
    public Future<List<BoardCert>> getBoardCert(int status) {
        List<BoardCert> boardCertList  = certDao.getBoardCert(status, UserUtil.getUserInfo().getId());
        return new AsyncResult<>(boardCertList);
    }

    @Async
    public Future<List<WoodCert>> getWoodCert(int status) {
        List<WoodCert> woodCertList  = certDao.getWoodCert(status, UserUtil.getUserInfo().getId());
        return new AsyncResult<>(woodCertList);
    }

    @Async
    public Future<List<PlantCert>> getPlantCert(int status) {
        List<PlantCert> plantCertList  = certDao.getPlantCert(status, UserUtil.getUserInfo().getId());
        return new AsyncResult<>(plantCertList);
    }
}

package com.forestry.service;

import com.forestry.bean.BoardCert;
import com.forestry.bean.WoodCert;
import com.forestry.dao.CertDao;
import com.forestry.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertService {
    @Autowired
    CertDao certDao;

    public int addWoodCert(WoodCert woodCert) {
        return certDao.addWoodCert(woodCert, UserUtil.getUserInfo().getId());
    }

    public int addBoardCert(BoardCert boardCert) {
        return certDao.addBoardCert(boardCert, UserUtil.getUserInfo().getId());
    }
}

package com.forestry.dao;

import com.forestry.bean.BoardCert;
import com.forestry.bean.WoodCert;

public interface CertDao {
    int addWoodCert(WoodCert woodCert, int userId);

    int addBoardCert(BoardCert boardCert, int userId);
}

package com.forestry.dao;

import com.forestry.bean.BoardCert;
import com.forestry.bean.WoodCert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CertDao {
    int addWoodCert(WoodCert woodCert, int userId);

    int addBoardCert(BoardCert boardCert, int userId);

    int getBoardCertAmount(int uid);

    int getWoodCertAmount(int uid);

    List<BoardCert> getBoardCert(@Param("status") int status); // xml中if取参数必须加@param注解

    List<WoodCert> getWoodCert(@Param("status") int status);
}

package com.forestry.dao;

import com.forestry.bean.BoardCert;
import com.forestry.bean.PlantCert;
import com.forestry.bean.WoodCert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CertDao {
    int addWoodCert(WoodCert woodCert, int userId);

    int addBoardCert(BoardCert boardCert, int userId);

    int addPlantCert(PlantCert plantCert, int userId);

    int addPlantCertPicture(String id, String picture_url, String picture_location, String picture_time, int userId);

    int getBoardCertAmount(int uid);

    int getWoodCertAmount(int uid);

    List<BoardCert> getBoardCert(@Param("status") int status, @Param("uid") int uid); // xml中if取参数必须加@param注解

    List<WoodCert> getWoodCert(@Param("status") int status, @Param("uid") int uid);

    List<PlantCert> getPlantCert(@Param("status") int status, @Param("uid") int uid);
}

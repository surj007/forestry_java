package com.forestry.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.forestry.bean.BoardCert;
import com.forestry.bean.PlantCert;
import com.forestry.bean.WoodCert;

public interface CertDao {
    public int addWoodCert(WoodCert woodCert, int userId);

    public int addBoardCert(BoardCert boardCert, int userId);

    public int addPlantCert(PlantCert plantCert, int userId);

    public int addPlantCertPicture(String id, String picture_url, String picture_location, String picture_time, int userId);

    public int getBoardCertAmount(int uid);

    public int getWoodCertAmount(int uid);

    // xml中if取参数必须加@Param注解（感觉不需要加@Param）
    public List<BoardCert> getBoardCert(@Param("status") int status, @Param("uid") int uid);

    public List<WoodCert> getWoodCert(@Param("status") int status, @Param("uid") int uid);

    public List<PlantCert> getPlantCert(@Param("status") int status, @Param("uid") int uid);
}

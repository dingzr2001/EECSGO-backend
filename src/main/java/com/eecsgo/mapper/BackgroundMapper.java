package com.eecsgo.mapper;

import com.eecsgo.entity.Background;
import com.eecsgo.request.AddBgRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BackgroundMapper {
    void addBg(@Param("addBgRequest") AddBgRequest addBgRequest, @Param("creatorId") Integer creatorId, @Param("createDate") Date createDate);
    List<Background> getBg(@Param("creatorId") Integer creatorId);
}

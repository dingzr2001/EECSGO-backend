package com.eecsgo.mapper;

import com.eecsgo.entity.Background;
import com.eecsgo.entity.DataPoint;
import com.eecsgo.request.AddDpRequest;
import com.eecsgo.utils.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface DataPointMapper {
    List<DataPoint> getDpsByProgramId(@Param("programId") Integer programId, @Param("rowOffset") Integer rowOffset, @Param("pageSize") Integer pageSize);
    Integer getDpCounts(Integer programId);
    Integer checkDuplicateDp(@Param("programId") Integer programId, @Param("creatorId") Integer creatorId, @Param("bgId") Integer bgId);
    Integer addDp(@Param("addDpRequest") AddDpRequest addDpRequest, @Param("creatorId") Integer creatorId, @Param("createDate") Date createDate);
}

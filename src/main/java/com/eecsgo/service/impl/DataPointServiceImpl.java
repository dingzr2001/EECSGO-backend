package com.eecsgo.service.impl;

import com.eecsgo.entity.DataPoint;
import com.eecsgo.entity.response.DataPointResponse;
import com.eecsgo.exception.BusinessException;
import com.eecsgo.mapper.DataPointMapper;
import com.eecsgo.request.AddDpRequest;
import com.eecsgo.service.DataPointService;
import com.eecsgo.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DataPointServiceImpl implements DataPointService {
    @Resource
    public DataPointMapper dpMapper;
    public DataPointResponse getDPsByProgramId(Integer programId, Integer pageIndex, Integer pageSize){
        Integer rowOffset = pageIndex * pageSize;
        List<DataPoint> dps = dpMapper.getDpsByProgramId(programId, rowOffset, pageSize);
        Integer dpCount = dpMapper.getDpCounts(programId);
        DataPointResponse dpResponse = new DataPointResponse(dpCount, dps);
        System.out.println(dpResponse);
        return dpResponse;
    }

    public Boolean checkDuplicateDp(AddDpRequest addDpRequest, Integer userId){
        Integer programId = addDpRequest.getProgramId();
        Integer bgId = addDpRequest.getBgId();
        if(dpMapper.checkDuplicateDp(programId, userId, bgId) > 0) return true;
        return false;
    }

    public Integer addDp(AddDpRequest addDpRequest, Integer creatorId){
        Integer programId = addDpRequest.getProgramId();
        Integer bgId = addDpRequest.getBgId();
        if(dpMapper.checkDuplicateDp(programId, creatorId, bgId) > 0){
            throw new BusinessException(500, "重复分享背景数据");
        };
        Date createDate = new Date();
        return  dpMapper.addDp(addDpRequest, creatorId, createDate);
    }
}

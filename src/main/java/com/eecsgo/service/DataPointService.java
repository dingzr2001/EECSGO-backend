package com.eecsgo.service;

import com.eecsgo.entity.DataPoint;
import com.eecsgo.entity.response.DataPointResponse;
import com.eecsgo.request.AddDpRequest;
import com.eecsgo.utils.Result;

import java.util.List;

public interface DataPointService {
    DataPointResponse getDPsByProgramId(Integer id, Integer pageIndex, Integer pageSize);
    Boolean checkDuplicateDp(AddDpRequest addDpRequest, Integer userId);
    Integer addDp(AddDpRequest addDpRequest, Integer creatorId);
}

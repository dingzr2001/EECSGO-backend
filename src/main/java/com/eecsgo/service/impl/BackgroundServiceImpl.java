package com.eecsgo.service.impl;

import com.eecsgo.entity.Background;
import com.eecsgo.mapper.BackgroundMapper;
import com.eecsgo.request.AddBgRequest;
import com.eecsgo.service.BackgroundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BackgroundServiceImpl implements BackgroundService {
    @Resource
    private BackgroundMapper bgMapper;
    public Integer addBg(AddBgRequest addBgRequest, Integer creatorId){
        Date createDate = new Date();
        bgMapper.addBg(addBgRequest, creatorId, createDate);
        return 1;
    }

    public List<Background> getBg(Integer creatorId){
        return bgMapper.getBg(creatorId);
    }


}

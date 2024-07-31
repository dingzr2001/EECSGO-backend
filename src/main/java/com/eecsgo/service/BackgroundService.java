package com.eecsgo.service;

import com.eecsgo.entity.Background;
import com.eecsgo.request.AddBgRequest;

import java.util.List;

public interface BackgroundService {
    Integer addBg(AddBgRequest addBgRequest, Integer creatorId);
    List<Background> getBg(Integer creatorId);
//    void deleteBg(Integer bgId);
}

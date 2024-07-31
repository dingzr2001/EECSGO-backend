package com.eecsgo.service.impl;

import com.eecsgo.entity.Program;
import com.eecsgo.exception.BusinessException;
import com.eecsgo.mapper.ProgramMapper;
import com.eecsgo.request.ProgramRequest;
import com.eecsgo.service.ProgramService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {
    @Resource
    private ProgramMapper programMapper;
    @Override
    public List<Program> getByName(String name) {
        return programMapper.list();
    }
    public List<Program> getProgram(String name, String nameAbbr, String university, String major){
        return programMapper.getProgram(name, nameAbbr, university, major);
    }

    public void insertProgram(ProgramRequest programRequest) {
        if(programMapper.checkDuplicateProgram(programRequest.getNameAbbr()) > 0){
            throw new BusinessException(500, "项目已存在");
        }
        programMapper.insertProgram(programRequest);
    }


    public void deleteProgram(Integer id){
        programMapper.deleteProgram(id);
    }


    public void insertAuditProgram(ProgramRequest programRequest, Integer creatorId, Boolean auditStatus){
        if(programMapper.checkDuplicateAuditProgram(programRequest.getNameAbbr() ) > 0){
            throw new BusinessException(500, "项目已在审核列表");
        }
        programMapper.insertAuditProgram(programRequest, creatorId, auditStatus);
    }

}

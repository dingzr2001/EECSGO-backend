package com.eecsgo.service;

import com.eecsgo.entity.AuditProgram;
import com.eecsgo.entity.Program;
import com.eecsgo.request.ProgramRequest;

import java.util.List;

public interface ProgramService {
//    public List<Program> list();
    List<Program> getByName(String name);
    List<Program> getProgram(String name, String nameAbbr, String university, String major);
    void insertProgram(ProgramRequest programRequest);
    void insertAuditProgram(ProgramRequest programRequest, Integer creatorId, Boolean auditStatus);
    void deleteProgram(Integer id);
}

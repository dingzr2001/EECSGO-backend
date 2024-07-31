package com.eecsgo.service;

import com.eecsgo.entity.AuditProgram;
import com.eecsgo.entity.Program;
import com.eecsgo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    List<User> getUserList(Integer pageIndex, Integer pageSize);
    Integer removeUser(Integer userId);
    Integer setAdmin(User operator, User operatedUser);

    List<AuditProgram> getAuditProgramList(Integer pageIndex, Integer pageSize);
    Integer approveProgram(AuditProgram auditProgram);
}

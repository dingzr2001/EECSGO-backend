package com.eecsgo.service.impl;

import com.eecsgo.entity.AuditProgram;
import com.eecsgo.entity.User;
import com.eecsgo.exception.BusinessException;
import com.eecsgo.mapper.ProgramMapper;
import com.eecsgo.mapper.UserMapper;
import com.eecsgo.request.ProgramRequest;
import com.eecsgo.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProgramMapper programMapper;
    public List<User> getUserList(Integer pageIndex, Integer pageSize){
        Integer rowOffset = pageIndex * pageSize;
        List<User> userList = userMapper.getUserList(rowOffset, pageSize);
        return userList;
    }

    public Integer removeUser(Integer userId){
        User user = userMapper.getUserById(userId);
        if(user == null){
            throw new BusinessException(500, "用户不存在");
        }
        return -1;
        //TODO: 实现删除用户功能
    }
    public Integer setAdmin(User operator, User operatedUser){
        return -1;
        //TODO: 实现提升用户等级功能
    }

    public List<AuditProgram> getAuditProgramList(Integer pageIndex, Integer pageSize){
        Integer rowOffset = pageIndex * pageSize;
        return programMapper.getAuditProgramList(rowOffset, pageSize);
    }

    public Integer approveProgram(AuditProgram auditProgram){
        programMapper.deleteAuditProgram(auditProgram.getId());
        ProgramRequest programRequest = new ProgramRequest(
                auditProgram.getNameAbbr(),
                auditProgram.getUniversity(),
                auditProgram.getName(),
                auditProgram.getMajor(),
                auditProgram.getUrl(),
                auditProgram.getRegion(),
                auditProgram.getLocation(),
                auditProgram.getToefl(),
                auditProgram.getToeflL(),
                auditProgram.getToeflR(),
                auditProgram.getToeflS(),
                auditProgram.getToeflW(),
                auditProgram.getIelts(),
                auditProgram.getIeltsL(),
                auditProgram.getIeltsR(),
                auditProgram.getIeltsS(),
                auditProgram.getIeltsW(),
                auditProgram.getMinDuration(),
                auditProgram.getMaxDuration()
        );
        programMapper.insertProgram(programRequest);
        return 1;
    }
}

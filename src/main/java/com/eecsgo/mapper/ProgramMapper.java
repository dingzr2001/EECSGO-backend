package com.eecsgo.mapper;

import com.eecsgo.entity.AuditProgram;
import com.eecsgo.entity.Program;
import com.eecsgo.request.ProgramRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProgramMapper {
    @Select("select * from programs")
    List<Program> list();
    List<Program> getProgram(@Param("name")String name, @Param("nameAbbr")String nameAbbr,@Param("university") String university, @Param("major")String major);
    Integer checkDuplicateProgram(String nameAbbr);

    void insertProgram(@Param("programRequest") ProgramRequest programRequest);

    void deleteProgram(Integer id);

    Integer checkDuplicateAuditProgram(String nameAbbr);
    void insertAuditProgram(@Param("programRequest") ProgramRequest programRequest, @Param("creatorId") Integer creatorId, @Param("auditStatus") Boolean auditStatus);
    List<AuditProgram> getAuditProgramList(@Param("rowOffset") Integer rowOffset, @Param("pageSize") Integer pageSize);
    void deleteAuditProgram(Integer id);
}

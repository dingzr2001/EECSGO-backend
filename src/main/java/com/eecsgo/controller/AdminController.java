package com.eecsgo.controller;

import com.eecsgo.entity.AuditProgram;
import com.eecsgo.entity.Program;
import com.eecsgo.entity.User;
import com.eecsgo.service.AdminService;
import com.eecsgo.service.UserService;
import com.eecsgo.utils.JwtUtil;
import com.eecsgo.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private UserService userService;
    @GetMapping("/admin/programs")
    public Result<List<AuditProgram>> getProgramList(@RequestHeader("Authorization") String token, Integer pageIndex, Integer pageSize){
        System.out.println("进入管理项目接口");
        Integer userId = JwtUtil.getIdFromToken(token);
        User user = userService.getUserById(userId);
        if(user == null) return Result.error(401, "用户未登录");
        if(user.getRole() <= 1) return Result.error(401, "用户无权限");
        List<AuditProgram> auditProgramList = adminService.getAuditProgramList(pageIndex, pageSize);
        return Result.success(auditProgramList);
    }
    @PostMapping("admin/programs/approve")
    public Result<String> approveProgram(@RequestHeader(name = "Authorization") String token, @RequestBody AuditProgram auditProgram){
        Integer userId = JwtUtil.getIdFromToken(token);
        User user = userService.getUserById(userId);
        if(user.getRole() > 1){
            adminService.approveProgram(auditProgram);
        }
        String msg = "审核通过";
        return Result.success(msg);
    }
}

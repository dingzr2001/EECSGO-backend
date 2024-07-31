package com.eecsgo.controller;

import com.eecsgo.entity.DataPoint;
import com.eecsgo.entity.Program;
import com.eecsgo.entity.User;
import com.eecsgo.entity.response.DataPointResponse;
import com.eecsgo.request.AddDpRequest;
import com.eecsgo.request.ProgramRequest;
import com.eecsgo.service.DataPointService;
import com.eecsgo.service.ProgramService;
import com.eecsgo.service.UserService;
import com.eecsgo.utils.JwtUtil;
import com.eecsgo.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/program")
public class ProgramController {
    @Resource
    private ProgramService programService;
    @Resource
    private DataPointService dpService;
    @Resource
    private UserService userService;
    @GetMapping
    public Result<List<Program>> getProgram(String name, String nameAbbr, String university, String major){
//        List<Program> program = programService.getByName(name);
        List<Program> programs = programService.getProgram(name, nameAbbr, university, major);
        return Result.success(programs);
    }

    @PostMapping("/add")
    public Result<String> addProgram(@RequestHeader(name = "Authorization") String token, @RequestBody ProgramRequest program){
        Integer id = JwtUtil.getIdFromToken(token);
        User user = userService.getUserById(id);
        if(user == null) return Result.error(401, "用户不存在");
        if(user.getRole() >= 2) {   //用户权限大于2，说明有权添加项目
            programService.insertProgram(program);
            return Result.success("新建项目成功");
        }
        else{   //用户权限为1，提交后需要管理员审核
            programService.insertAuditProgram(program, id, false);
            return Result.success("已提交，待管理员审核");
        }
//        return Result.error(401, "您没有权限新建项目");
    }

    @DeleteMapping("/{id}")
    public void deleteProgram(@PathVariable Integer id){
        programService.deleteProgram(id);
    }
//    @GetMapping("/detail")
//    public Result<List<DataPoint>> getDPs(Integer id){
////        List<DataPoint> dps = dpService.getDPsByProgramId(id);
////        return Result.success(dps);
//    }
    @GetMapping("/dp")
    public Result<DataPointResponse> getDpsByProgramId(@RequestHeader(name="Authorization") String token, Integer programId, Integer pageIndex, Integer pageSize){
        Integer userId = JwtUtil.getIdFromToken(token);
        User user = userService.getUserById(userId);
        if(user == null) return Result.error(401, "用户未登录");
        System.out.println(programId +","+ pageIndex +","+ pageSize);
        DataPointResponse dpResponse = dpService.getDPsByProgramId(programId, pageIndex, pageSize);
        return Result.success(dpResponse);
    }

    @PostMapping("/dp/add")
    public Result<Integer> addDp(@RequestHeader (name="Authorization") String token, @RequestBody AddDpRequest addDpRequest){
        System.out.println("Dp是：" + addDpRequest);
        Integer userId = JwtUtil.getIdFromToken(token);
        User user = userService.getUserById(userId);
        if(user == null) return Result.error(401, "用户不存在");
        addDpRequest.setSubmitDate((Date) addDpRequest.getSubmitDate());
        Integer res = dpService.addDp(addDpRequest, userId);

        return Result.success(res);
    }


}

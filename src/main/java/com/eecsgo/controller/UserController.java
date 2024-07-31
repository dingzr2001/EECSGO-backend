package com.eecsgo.controller;

import com.eecsgo.entity.Background;
import com.eecsgo.entity.User;
import com.eecsgo.request.AddBgRequest;
import com.eecsgo.request.LoginRequest;
import com.eecsgo.request.RegisterRequest;
import com.eecsgo.request.VerificationCodeRequest;
import com.eecsgo.service.BackgroundService;
import com.eecsgo.service.UserService;
import com.eecsgo.utils.JwtUtil;
import com.eecsgo.utils.Result;
import com.eecsgo.utils.VerificationCodeStore;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
//    @GetMapping("/user")
//    public getUserInfo(@RequestParam(name))
    @Resource
    private UserService userService;
    private static final int EXPIRE_DURATION = 1000 * 3600 * 24 * 30;   //30天过期
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginRequest loginRequest){
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        System.out.println("entered login");
        if(user != null){
            System.out.println("user got");
            Map<String, Object> claims = new HashMap<>();
            claims.put("email", user.getEmail());
            claims.put("name", user.getUsername());
            claims.put("id", user.getId());
            String jwt = JwtUtil.generateToken(user.getId().toString(), claims, EXPIRE_DURATION);
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("user", claims);
            responseBody.put("token", jwt);
            return Result.success(responseBody);
        }

        return Result.error(500, "用户名或密码错误");

    }

    @GetMapping("/user")
    public Result<User> getUserById(@RequestHeader(name="Authorization") String token, Integer id){
        Integer userId = JwtUtil.getIdFromToken(token);
        User user = userService.getUserById(userId);
        if(user != null){
            System.out.println("user got");
            return Result.success(user);
        }
        System.out.println("不对");
        return Result.error(500, "用户名或密码错误");

    }

    @Resource
    private BackgroundService bgService;
    @PostMapping("/user/bg/add")
    public Result<Integer> addBg(@RequestHeader(name="Authorization") String token, @RequestBody AddBgRequest addBgRequest) {
        Integer creatorId = JwtUtil.getIdFromToken(token);
        User user = userService.getUserById(creatorId);
        if (user == null) return Result.error(500, "用户未登录");
        Integer res = bgService.addBg(addBgRequest, creatorId);
        if (res > 0) return Result.success(res);
        return Result.error(500, "新建失败");
    }

    @GetMapping("/user/bg")
    public Result<List<Background>> getBg(@RequestHeader(name = "Authorization") String token){
        Integer creatorId = JwtUtil.getIdFromToken(token);
        List<Background> bgs = bgService.getBg(creatorId);
        return Result.success(bgs);
    }


    @PostMapping("/register/verification")
    public Result<String> sendVerificationCode(@RequestBody VerificationCodeRequest verificationCodeRequest){
        System.out.println("进入");
        String email = verificationCodeRequest.getEmail();
        String msg = "Verification Code Sent to " + email;
        userService.sendVerification(email);
        return Result.success(msg);
    }

//    public Result<String> checkVerificationCode(@RequestBody Ve)
    @PostMapping("register/register")
    public Result<Integer> register(@RequestBody RegisterRequest registerRequest){
        Integer res = userService.addUser(registerRequest);
        return Result.success(res);
    }
}

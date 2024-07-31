package com.eecsgo.service.impl;

import com.eecsgo.entity.User;
import com.eecsgo.exception.BusinessException;
import com.eecsgo.mapper.UserMapper;
import com.eecsgo.request.RegisterRequest;
import com.eecsgo.service.UserService;
import com.eecsgo.utils.VerificationCodeStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public User login(String email, String password){
        return userMapper.login(email, password);
    }
    public User getUserById(Integer id){return userMapper.getUserById(id);}

    @Value("${spring.mail.username}")
    private String fromUser;

    @Resource
    private VerificationCodeStore verificationCodeStore;
    @Resource
    private JavaMailSender javaMailSender;
    public String sendVerification(String email){
        String code = verificationCodeStore.generateCode(email);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromUser);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("EECSGO平台-邮箱验证");
        simpleMailMessage.setText("您好，欢迎使用EECSGO平台，您的邮箱验证码是：" + code + "\n请不要泄露给其他人，有效期1分钟，过期请重新发送。");
        javaMailSender.send(simpleMailMessage);
        return "验证码已发送至" + email;
    }

    public Boolean checkDuplicateUsername(String username){
        if(userMapper.checkDuplicateUsername(username) > 0) return true;
        return false;
    }

    public Boolean checkDuplicateEmail(String email){
        if(userMapper.checkDuplicateEmail(email) > 0) return true;
        return false;
    }

    public Integer addUser(RegisterRequest registerRequest) {
        if(verificationCodeStore.checkCode(registerRequest.getEmail(), registerRequest.getVerificationCode())){
            throw new BusinessException(500, "验证码不正确！");
        }
        if(checkDuplicateEmail(registerRequest.getEmail())){
            throw new BusinessException(500, "邮箱已被注册");
        } else {
            if(checkDuplicateUsername(registerRequest.getUsername())){
                throw new BusinessException(500, "用户名已存在");
            }
            else{
                Date registerDate = new Date();
                Integer role = 1;
                return userMapper.addUser(registerRequest, registerDate, 1);
            }
        }

    }
}

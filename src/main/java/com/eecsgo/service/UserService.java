package com.eecsgo.service;

import com.eecsgo.entity.User;
import com.eecsgo.request.RegisterRequest;
import com.eecsgo.utils.VerificationCodeStore;
import org.springframework.mail.SimpleMailMessage;

public interface UserService {
    User login(String email, String password);
    User getUserById(Integer id);
    String sendVerification(String email);
    Integer addUser(RegisterRequest registerRequest);
}

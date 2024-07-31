package com.eecsgo.mapper;

import com.eecsgo.entity.User;
import com.eecsgo.request.RegisterRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    User login(@Param("email") String email, @Param("password") String password);
    User getUserById(@Param("id") Integer id);
    Integer checkDuplicateUsername(String username);
    Integer checkDuplicateEmail(String email);
    Integer addUser(@Param("registerRequest") RegisterRequest registerRequest, @Param("registerDate") Date registerDate, @Param("role") Integer role);
    Integer getUserRole(String userId);
    Integer upgradeUserRole(@Param("operatedUserId") String operatedUserId, @Param("role") Integer role);
    List<User> getUserList(@Param("rowOffset") Integer rowOffset, @Param("pageSize") Integer pageSize);
}

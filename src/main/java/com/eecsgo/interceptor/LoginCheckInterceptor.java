package com.eecsgo.interceptor;

import com.eecsgo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 JWT 令牌
        String token = request.getHeader("Authorization");

        try {
            // 验证 JWT 令牌的有效性
            Claims claims = JwtUtil.parseToken(token);
            System.out.println(claims);

        } catch (ExpiredJwtException e) {
            // 处理令牌过期的情况
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer error=\"expired_token\"");
            return false;
        } catch (Exception e) {
            // 处理其他异常情况
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        // 在请求头中添加一些通用的请求头
//        request.setAttribute("userId", claims.getSubject());
//        request.setAttribute(HttpHeaders.CONTENT_TYPE, "application/json");
//        request.setAttribute(HttpHeaders.ACCEPT, "application/json");

        // 允许请求继续处理
        return true;
    }
}

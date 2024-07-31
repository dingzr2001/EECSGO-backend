package com.eecsgo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String SIGN_KEY = "your_secret_key";

    /**
     * 生成 JWT 令牌
     *
     * @param id       用户 ID
     * @param claims  主题
     * @param ttlMillis 过期时间（毫秒）
     * @return JWT 字符串
     */
    public static String generateToken(String id, Map<String, Object> claims, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 创建 JWT 头部
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", signatureAlgorithm.getValue());

        // 创建 JWT 负载
        JwtBuilder builder = Jwts.builder()
                .setHeader(header)
                .setId(id)
                .setIssuedAt(now)
                .setClaims(claims)
                .signWith(signatureAlgorithm, SIGN_KEY);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    /**
     * 解析 JWT 令牌
     *
     * @param token JWT 字符串
     * @return JWT 负载
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGN_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static Integer getIdFromToken(String token){
        return (Integer) parseToken(token).get("id");
    }


//    private static Key getSecret() {
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
//        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
//    }
}

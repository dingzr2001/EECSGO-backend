package com.eecsgo.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

@Component("VerificationCodeStore")
public class VerificationCodeStore {

    private static final String KEY_PREFIX = "PineapplePie1";
    private static final int EXPIRE_TIME = 300;  //单位：秒
    private static final String HASH_MAP_NAME = "VerificationCodeHashMap";
    @Resource
    private RedisTemplate redisTemplate;

    public String getCodeFromEmail(String email){
        String key = KEY_PREFIX + email;
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        return hashOps.get(HASH_MAP_NAME, key);
    }

    public Boolean checkCode(String email, String code){
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(code)) return false;

        String codeInRedis = this.getCodeFromEmail(email);
        if(StringUtils.isEmpty(codeInRedis)) return false;
        if(code.equals(codeInRedis)) return true;
        return false;
    }

    public String generateCode(String email){
        String key = KEY_PREFIX + email;
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        String code = this.randomCode();
        hashOps.put(HASH_MAP_NAME, key, code);
        if(hashOps.get(HASH_MAP_NAME, key).equals(code)) System.out.println("成功！");
        return code;
    }

    public String randomCode(){
        Random r = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++){
            int randomNum = r.nextInt(10);
            code.append(randomNum);
        }
        return code.toString();
    }
}

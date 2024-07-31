package com.eecsgo.utils;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static Result success() {
        Result result = new Result<>();
        result.setCode(200);
        result.setMsg("OK");
        return result;
    }

    public static<T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("OK");
        result.setData(data);
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}

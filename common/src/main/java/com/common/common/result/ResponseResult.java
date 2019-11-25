package com.common.common.result;

/**
 * @ClassName ResponseResult
 * @Author Wen.GD
 * @Date 2019/5/27 20:36
 **/
public class ResponseResult<T> {

    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public ResponseResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseResult() {

    }

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

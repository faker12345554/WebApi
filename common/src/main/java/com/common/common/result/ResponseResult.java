package com.common.common.result;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ResponseResult
 * @Author Wen.GD
 * @Date 2019/5/27 20:36
 **/
public class ResponseResult<T> {
    @ApiModelProperty(value = "响应编码",dataType = "Integer")
    private Integer code;
    @ApiModelProperty(value = "提示信息",dataType = "String")
    private String message;
    @ApiModelProperty(value = "输出结果",dataType = "Object")
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

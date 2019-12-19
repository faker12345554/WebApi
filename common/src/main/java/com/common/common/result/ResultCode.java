package com.common.common.result;

/**
 * @ClassName ResultCode
 * @Author Wen.GD
 * @Date 2019/5/27 20:20
 **/
public enum ResultCode {
    SUCCESS(200, "成功"),
    NO_PERMISSION(211, "权限不足"),
    SERVER_ERROR(10000, "服务器异常"),
    AUTH_ERROR(10001, "认证失败"),

    PARAMS_ERROR(10002, "参数错误"),
    JSON_PARSE_ERROR(10003, "JSON解析错误"),
    ILLEAGAL_STRING(15001, "非法字符串"),
    UNKNOW_ERROR(16000, "未知错误"),
    DATA_DUPLICATION(1,"数据重复"),
    NULLDATA(2,"没有任何数据");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public ResultCode setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultCode setMessage(String message) {
        this.message = message;
        return this;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

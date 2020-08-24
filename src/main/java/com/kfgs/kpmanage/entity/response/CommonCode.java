package com.kfgs.kpmanage.entity.response;

import lombok.ToString;

@ToString
public enum  CommonCode implements ResultCode {

    SUCCESS(true,20000,"操作成功"),
    FAIL(false,11111,"操作失败"),
    SERVER_ERROR(false,10002,"权限不足，无法操作"),
    INVALID_PARAM(false,10003,"非法参数");

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;


    private CommonCode(Boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}

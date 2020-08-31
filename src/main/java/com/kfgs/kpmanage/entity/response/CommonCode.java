package com.kfgs.kpmanage.entity.response;

import lombok.ToString;

@ToString
public enum  CommonCode implements ResultCode {

    SUCCESS(true,20000,"操作成功"),
    FAIL(false,99999,"操作失败"),

    /**
     * 业务错误:10001~19999
     */
    NO_PERMISSION(false,10001,"权限不足，无法操作"),

    /**
     * 参数错误:30001~39999
     */
    PARAM_NOT_VALID(false,30001,"非法参数"),
    PARAM_IS_BLANK(false,30002,"参数为空"),
    PARAM_TYPE_ERROR(false,30003,"参数类型错误"),
    PARAM_NOT_COMPLETE(false,30004,"参数缺失"),

    /**
     * 用户错误
     */
    USER_NOT_LOGIN(false,20001,"用户未登录"),
    USER_ACCOUNT_EXPIRED(false,20002,"账号已过期"),
    USER_CREDENTIALS_ERROR(false,20003,"密码错误"),
    USER_CREDENTIALS_EXPORED(false,20004,"密码过期"),
    USER_ACCOUNT_DISABLE(false,20005,"账号不可用"),
    USER_ACCOUNT_LOCKED(false,20006,"账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(false,20007,"账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(false,20008,"账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(false,20009,"账号下线");

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

package com.kfgs.kpmanage.entity.response;

import lombok.ToString;

@ToString
public enum UploadCode implements ResultCode {

    FILE_NOT_FOUND(false,20001,"找不到对应文件"),
    FILE_IS_NULL(false,20002,"文件为空"),
    FILE_EXISTS(false,20003,"文件已经存在"),
    FILE_IO_ERROR(false,20004,"文件写入错误")
    ;

    private UploadCode(Boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    @Override
    public boolean success() {
        return false;
    }

    @Override
    public int code() {
        return 0;
    }

    @Override
    public String message() {
        return null;
    }
}

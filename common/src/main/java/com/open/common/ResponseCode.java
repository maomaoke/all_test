package com.open.common;

/**
 * @AUTHOR CHEN-KE-CHAO
 * @DATE 2018-11-08-10:03
 * @FUNCATION 全局异常处理拦截类
 */

public enum  ResponseCode {
    SUCCESS(0, "成功"),
    ERROR(1, "错误"),
    ILLEGAL_ARGUMENT(11, "非法参数");


    private final int code;

    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

}

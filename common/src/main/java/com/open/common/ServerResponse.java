package com.open.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @AUTHOR CHEN-KE-CHAO
 * @DATE 2018-11-08-10:03
 * @FUNCATION 全局异常处理拦截类
 */

@ApiModel("统一返回数据格式")
public class ServerResponse<T> implements Serializable {
    @ApiModelProperty(value = "状态;0:成功;其他为error", example = "0", required = true)
    private int status;
    @ApiModelProperty(value = "错误信息", example = "error")
    private String msg;
    @ApiModelProperty(value = "返回的内容数据", example = "{}")
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }



    //该注解可以让public方法不被序列化
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }


    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), data, msg);
    }

    public static <T> ServerResponse<T> createByError() {

        return new ServerResponse<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
}

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {

        return new ServerResponse<>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse<>(errorCode, errorMessage);
    }

    public static ServerResponse create(ResponseCode responseCode) {
        return new ServerResponse(responseCode.getCode(), responseCode.getDesc());
    }

    public static <T> ServerResponse<T> create(T data, ResponseCode responseCode) {
        return new ServerResponse<>(responseCode.getCode(), data, responseCode.getDesc());
    }

    public static <T> ServerResponse<T> createByError(int errorCode, T data, String errorMessage) {
        return new ServerResponse<>(errorCode, data, errorMessage);
    }
}

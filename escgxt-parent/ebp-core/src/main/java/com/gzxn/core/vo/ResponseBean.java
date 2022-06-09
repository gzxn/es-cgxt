package com.gzxn.core.vo;

import com.gzxn.core.constant.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ResponseBean
 * @Author: CodeBird
 * @Date: 2022-03-02 21:11
 * @Description: 数据传输响应对象
 */
@Data
@AllArgsConstructor
public class ResponseBean<T> implements Serializable {

    @ApiModelProperty(value = "操作码")
    private Integer code;
    @ApiModelProperty(value = "响应信息")
    private String msg;
    @ApiModelProperty(value = "数据")
    private T data;

    public static <R> ResponseBean<R> create(Integer code, String msg, R data) {
        return new ResponseBean<R>(code, msg, data);
    }

    public static <R> ResponseBean<R> Success() {
        return Success(Constant.RESPONSE_MSG_SUCCESS, null);
    }

    public static <R> ResponseBean<R> Success(R data) {
        return Success(Constant.RESPONSE_MSG_SUCCESS, data);
    }

    public static <R> ResponseBean<R> Success(String msg) {
        return Success(msg, null);
    }

    public static <R> ResponseBean<R> Success(String msg, R data) {
        return create(Constant.RESPONSE_CODE_SUCCESS, msg, data);
    }

    public static <R> ResponseBean<R> Fail() {
        return Fail(Constant.RESPONSE_MSG_FAIL, null);
    }

    public static <R> ResponseBean<R> Fail(R data) {
        return Fail(Constant.RESPONSE_MSG_FAIL, data);
    }

    public static <R> ResponseBean<R> Fail(String msg) {
        return Fail(msg, null);
    }

    public static <R> ResponseBean<R> Fail(String msg, R data) {
        return create(Constant.RESPONSE_CODE_FAIL, msg, data);
    }

    public static <R> ResponseBean<R> Exception() {
        return Exception(Constant.RESPONSE_MSG_EXCEPTION, null);
    }

    public static <R> ResponseBean<R> Exception(R data) {
        return Exception(Constant.RESPONSE_MSG_EXCEPTION, data);
    }

    public static <R> ResponseBean<R> Exception(String msg) {
        return Exception(msg, null);
    }

    public static <R> ResponseBean<R> Exception(String msg, R data) {
        return create(Constant.RESPONSE_CODE_FAIL, msg, data);
    }

}

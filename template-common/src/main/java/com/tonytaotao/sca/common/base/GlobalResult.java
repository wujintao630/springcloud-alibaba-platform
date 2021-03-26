package com.tonytaotao.sca.common.base;

import lombok.Data;

/**
 * 全局结果返回
 * @author tonytaotao
 */
@Data
public class GlobalResult<T> {

    private static String SUCCESS_CODE = "0";
    private static String FAILURE_CODE = "-1";

    private String code;

    private String msg;

    private Boolean success;

    private T data;

    private String requestId;

    public GlobalResult(String code, String msg, Boolean success, T data) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public static GlobalResult DefaultSuccess() {
        return DefaultSuccess("OK");
    }

    public static<T> GlobalResult DefaultSuccess(T data) {
        return DefaultSuccess(SUCCESS_CODE, "OK", data);
    }

    public static GlobalResult DefaultSuccess(String msg) {
        return DefaultSuccess(SUCCESS_CODE, msg);
    }

    public static GlobalResult DefaultSuccess(String code, String msg) {
        return DefaultSuccess(code, msg, null);
    }

    public static<T> GlobalResult DefaultSuccess(String code, String msg, T data) {
        return new GlobalResult(code, msg, Boolean.TRUE, data);
    }

    public static GlobalResult DefaultFailure() {
       return DefaultFailure("系统异常");
    }

    public static GlobalResult DefaultFailure(String msg) {
        return DefaultFailure(FAILURE_CODE, msg);
    }

    public static GlobalResult DefaultFailure(String code, String msg) {
        return new GlobalResult(code, msg, Boolean.FALSE, null);
    }

    public boolean isSuccess() {
        return success.booleanValue() == Boolean.TRUE.booleanValue();
    }

    public boolean isFailure() {
        return success.booleanValue() == Boolean.FALSE.booleanValue();
    }
}

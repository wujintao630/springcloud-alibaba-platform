package com.tonytaotao.scp.common.base;

/**
 * 全局结果返回
 * @author tonytaotao
 */
public class GlobalResult<T> {

    private static String SUCCESS_CODE = "0";
    private static String FAILURE_CODE = "-1";

    private String code;

    private String msg;

    private Boolean success;

    private T data;

    private String requestId;

    public GlobalResult() {
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GlobalResult{");
        sb.append("code='").append(code).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", success=").append(success);
        sb.append(", data=").append(data);
        sb.append(", requestId='").append(requestId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

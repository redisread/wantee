package com.jiahongw.wantee.constant.enums;

/**
 * 网关异常枚举
 *
 * @author VictorHong
 * @date 2023/1/13
 */
public enum GatewayExceptionCodeEnum {

    /**
     * 通用业务异常
     */
    COMMON_GATEWAY_EXCEPTION(1, "通用网关异常"),
    TIME_OUT(2, "调用超时");


    private Integer code;
    private String msg;

    GatewayExceptionCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

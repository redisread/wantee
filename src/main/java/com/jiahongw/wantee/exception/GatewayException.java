package com.jiahongw.wantee.exception;

import com.google.common.base.Joiner;
import com.jiahongw.wantee.common.enums.GatewayExceptionCodeEnum;

/**
 * 网关异常
 *
 * @author VictorHong
 * @date 2023/1/13
 */
public class GatewayException extends RuntimeException {

    private GatewayExceptionCodeEnum exceptionCodeEnum = GatewayExceptionCodeEnum.COMMON_GATEWAY_EXCEPTION;

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayException(GatewayExceptionCodeEnum gatewayExceptionCodeEnum) {
        super(gatewayExceptionCodeEnum.getMsg());
        this.exceptionCodeEnum = gatewayExceptionCodeEnum;
    }

    public GatewayException(GatewayExceptionCodeEnum gatewayExceptionCodeEnum, String msg) {
        super(Joiner.on(",").join(gatewayExceptionCodeEnum.getMsg(), msg));
        this.exceptionCodeEnum = gatewayExceptionCodeEnum;
    }

    public GatewayException(GatewayExceptionCodeEnum gatewayExceptionCodeEnum,
        Throwable throwable) {
        super(gatewayExceptionCodeEnum.getMsg(), throwable);
        this.exceptionCodeEnum = gatewayExceptionCodeEnum;
    }

    public GatewayExceptionCodeEnum getExceptionCodeEnum() {
        return exceptionCodeEnum;
    }
}

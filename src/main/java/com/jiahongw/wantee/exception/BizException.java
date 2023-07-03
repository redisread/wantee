package com.jiahongw.wantee.exception;

import com.google.common.base.Joiner;
import com.jiahongw.wantee.common.enums.BizExceptionCodeEnum;

/**
 * 业务异常
 *
 * @author VictorHong
 * @date 2023/1/13
 */
public class BizException extends RuntimeException {

    private BizExceptionCodeEnum exceptionCodeEnum = BizExceptionCodeEnum.COMMON_BIZ_EXCEPTION;


    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(BizExceptionCodeEnum bizExceptionCodeEnum) {
        super(bizExceptionCodeEnum.getMsg());
        this.exceptionCodeEnum = bizExceptionCodeEnum;
    }

    public BizException(BizExceptionCodeEnum bizExceptionCodeEnum, String msg) {
        super(Joiner.on(",").join(bizExceptionCodeEnum.getMsg(), msg));
        this.exceptionCodeEnum = bizExceptionCodeEnum;
    }

    public BizException(BizExceptionCodeEnum bizExceptionCodeEnum, Throwable throwable) {
        super(bizExceptionCodeEnum.getMsg(), throwable);
        this.exceptionCodeEnum = bizExceptionCodeEnum;
    }

    public BizExceptionCodeEnum getExceptionCodeEnum() {
        return exceptionCodeEnum;
    }
}

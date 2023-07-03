package com.jiahongw.wantee.common.enums;

/**
 * 业务异常枚举
 *
 * @author VictorHong
 * @date 2023/1/13
 */
public enum BizExceptionCodeEnum {

    FAIL(-1,"fail"),
    /**
     * 通用业务异常
     */
    COMMON_BIZ_EXCEPTION(1, "通用业务异常"),
    CREATE_NOTION_PAGE_EXCEPTION(2, "创建notion页面异常");


    private final Integer code;
    private final String msg;

    BizExceptionCodeEnum(Integer code, String msg) {
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

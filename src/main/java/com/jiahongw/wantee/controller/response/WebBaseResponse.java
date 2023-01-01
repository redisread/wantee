package com.jiahongw.wantee.controller.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 网页返回结果泛型类
 *
 * @author VictorHong
 * @date 2023/1/1
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WebBaseResponse<T> implements Serializable {

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    T data;

    /* ============ static tools ============= */

    public static <T> WebBaseResponse<T> build(int code, String msg, T data) {
        return new WebBaseResponse<>(code, msg, data);
    }

    public static <T> WebBaseResponse<T> success(T data) {
        return new WebBaseResponse<>(
            HttpStatus.OK.value(),
            "success",
            data
        );
    }

    public static <T> WebBaseResponse<T> error(int code, String msg) {
        return new WebBaseResponse<>(
            code,
            msg,
            null
        );
    }
}

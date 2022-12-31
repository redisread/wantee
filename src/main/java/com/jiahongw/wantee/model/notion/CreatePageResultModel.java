package com.jiahongw.wantee.model.notion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 创建Notion页面结果
 *
 * @author VictorHong
 * @date 2022/12/31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreatePageResultModel {

    /**
     * 返回状态信息
     */
    private HttpStatus httpStatus;

    /**
     * 创建页面的标题
     */
    private String title;

}

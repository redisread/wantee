package com.jiahongw.wantee.model.notion;

import lombok.Data;

/**
 * 创建卡片盒笔记需要的信息
 *
 * @author VictorHong
 * @date 2023/1/1
 */
@Data
public class CreateCardBoxPageModel {

    /**
     * 标题
     */
    private String title;

    /**
     * 类型，选择项目
     */
    private String type;

    /**
     * url
     */
    private String url;
}

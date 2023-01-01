package com.jiahongw.wantee.model.notion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Notion选择项模型，单选，多选，状态
 *
 * @author VictorHong
 * @date 2023/1/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OptionModel {

    /**
     * 选项id
     */
    private String id;

    /**
     * 选项名称
     */
    private String name;

    /**
     * 选项颜色
     */
    private String color;
}

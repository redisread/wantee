package com.jiahongw.wantee.model.notion;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Notion 选择项模型，包括单选、多选，状态（从database查询的数据）
 *
 * @author VictorHong
 * @date 2023/1/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SelectModel {

    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型，select、multi_select、status
     */
    private String type;

    /**
     * 选择项
     */
    private List<OptionModel> options;
}

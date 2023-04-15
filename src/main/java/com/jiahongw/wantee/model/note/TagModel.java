package com.jiahongw.wantee.model.note;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author VictorHong
 * @date 2023/4/15
 */
@Data
@ToString
@NoArgsConstructor
public class TagModel {

    private Long id;

    private String name;

    private Long creatorId;

    private LocalDateTime createTime;
}

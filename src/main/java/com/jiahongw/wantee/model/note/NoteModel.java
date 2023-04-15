package com.jiahongw.wantee.model.note;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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
public class NoteModel {

    private Long id;

    private Integer status;

    private List<Long> tagIdList;

    private List<Long> resourceIdList;

    private Long creatorId;

    private LocalDateTime createTime;

    private Long updaterId;

    private LocalDateTime updateTime;

}

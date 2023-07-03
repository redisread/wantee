package com.jiahongw.wantee.dto.note;

import com.jiahongw.wantee.common.enums.Visibility;
import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 * @author Redisread
 * @date 2023/5/14
 */
@Data
@ToString
public class UpdateNoteRequest {

    /**
     * 更新使用
     */
    private Integer id;

    private String content;

    private List<String> resourceIdList;

    private Visibility visibility;

    private Integer userId;
}

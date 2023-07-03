package com.jiahongw.wantee.dto.note;

import com.jiahongw.wantee.common.enums.Visibility;
import java.util.Date;
import lombok.Data;

/**
 * @author Redisread
 * @date 2023/5/14
 */
@Data
public class ListNoteRequest {

    private int page = 1;
    private int size = 20;
    private String tag;
    private Visibility visibility;
    private int userId;
    private Date begin;
    private Date end;
}

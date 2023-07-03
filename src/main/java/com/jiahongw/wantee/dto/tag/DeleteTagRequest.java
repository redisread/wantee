package com.jiahongw.wantee.dto.tag;

import java.util.List;
import lombok.Data;

/**
 * @author Redisread
 * @date 2023/5/20
 */
@Data
public class DeleteTagRequest {

    private List<String> tagNameList;

}

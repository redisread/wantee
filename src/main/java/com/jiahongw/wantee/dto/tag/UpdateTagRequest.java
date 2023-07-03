package com.jiahongw.wantee.dto.tag;

import jakarta.validation.constraints.NotEmpty;
import java.util.Map;
import lombok.Data;

/**
 * @author Redisread
 * @date 2023/5/20
 */
@Data
public class UpdateTagRequest {

    @NotEmpty
    private Map<Integer, String> tagId2NameMap;

}

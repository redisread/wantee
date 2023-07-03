package com.jiahongw.wantee.dto.resource;

import lombok.Data;

/**
 * @author Redisread
 * @date 2023/5/14
 */
@Data
public class UploadResourceResponse {
    private String publicId;
    private String url;
    private String suffix;
}

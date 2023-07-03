package com.jiahongw.wantee.dto.user;

import lombok.Data;

/**
 * @author Redisread
 * @date 2023/5/18
 */
@Data
public class UserUpdateRequest {

    private String displayName;
    private String email;
    private String bio;
    private String avatarUrl;
    private String password;
}

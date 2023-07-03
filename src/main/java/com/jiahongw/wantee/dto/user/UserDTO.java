package com.jiahongw.wantee.dto.user;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

/**
 * @author Redisread
 * @date 2023/5/14
 */
@Data
@ToString
public class UserDTO {

    private Integer id;

    private String username;

    private String email;

    private String displayName;

    private String bio;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    private String role;
    private String avatarUrl;

    private UserConfigDTO userConfig;
}

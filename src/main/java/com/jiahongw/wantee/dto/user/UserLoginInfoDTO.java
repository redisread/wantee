package com.jiahongw.wantee.dto.user;

import lombok.Data;
import lombok.ToString;

/**
 * @author Redisread
 * @date 2023/5/15
 */
@Data
@ToString
public class UserLoginInfoDTO {
    private String username;
    private String token;
    private String role;
}

package com.jiahongw.wantee.service;

import com.jiahongw.wantee.dto.user.UserDTO;
import com.jiahongw.wantee.dto.user.UserLoginRequest;
import com.jiahongw.wantee.dto.user.UserLoginInfoDTO;
import com.jiahongw.wantee.dto.user.UserRegisterRequest;
import com.jiahongw.wantee.dto.user.UserUpdateRequest;
import java.util.List;

/**
 * @author Redisread
 * @date 2023/5/15
 */
public interface UserService {

    UserLoginInfoDTO login(UserLoginRequest userLoginRequest);

    void logout();

    void register(UserRegisterRequest userRegisterRequest);

    void update(UserUpdateRequest userUpdateRequest);

    UserDTO getUserById(Integer id);

    UserDTO getUserByOpenId(String openId);

    UserDTO getCurrentUser();

    List<UserDTO> all();

    void deleteById(Integer id);
}

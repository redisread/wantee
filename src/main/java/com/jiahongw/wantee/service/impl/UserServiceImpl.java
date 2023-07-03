package com.jiahongw.wantee.service.impl;

import static com.jiahongw.wantee.entity.table.Tables.USER;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.jiahongw.wantee.common.enums.BizExceptionCodeEnum;
import com.jiahongw.wantee.common.enums.LoginTypeEnum;
import com.jiahongw.wantee.dto.user.UserDTO;
import com.jiahongw.wantee.dto.user.UserLoginRequest;
import com.jiahongw.wantee.dto.user.UserLoginInfoDTO;
import com.jiahongw.wantee.dto.user.UserRegisterRequest;
import com.jiahongw.wantee.dto.user.UserUpdateRequest;
import com.jiahongw.wantee.entity.User;
import com.jiahongw.wantee.exception.BizException;
import com.jiahongw.wantee.mapper.UserMapperExt;
import com.jiahongw.wantee.service.UserService;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Redisread
 * @date 2023/5/15
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapperExt userMapper;

    @Override
    public UserLoginInfoDTO login(UserLoginRequest userLoginRequest) {
        User user = userMapper.selectOneByQuery(
            QueryWrapper.create()
                .and(USER.USERNAME.eq(userLoginRequest.getUsername())));

        if (user == null) {
            throw new BizException(BizExceptionCodeEnum.FAIL, "用户不存在");
        }

        if (!BCrypt.checkpw(userLoginRequest.getPassword(), user.getPasswordHash())) {
            throw new BizException(BizExceptionCodeEnum.FAIL, "密码不正确");
        }
        UserLoginInfoDTO userLoginResponse = new UserLoginInfoDTO();
        StpUtil.login(user.getId(), LoginTypeEnum.WEB.name());
        userLoginResponse.setUsername(user.getUsername());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        userLoginResponse.setToken(tokenInfo.getTokenValue());
        userLoginResponse.setRole(user.getRole());
        return userLoginResponse;
    }

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPasswordHash(BCrypt.hashpw(userRegisterRequest.getPassword()));
        user.setUsername(userRegisterRequest.getUsername());
        user.setDisplayName(userRegisterRequest.getDisplayName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setBio(userRegisterRequest.getBio());
        userMapper.insertSelective(user);
    }

    @Override
    public void update(UserUpdateRequest userUpdateRequest) {
        Integer userId = StpUtil.getLoginIdAsInt();
        User user = userMapper.selectOneById(userId);
        if (Objects.isNull(user)) {
            throw new BizException(BizExceptionCodeEnum.FAIL, "用户不存在");
        }
        BeanUtils.copyProperties(userUpdateRequest, user);
        if (StringUtils.isNotEmpty(userUpdateRequest.getPassword())) {
            user.setPasswordHash(BCrypt.hashpw(userUpdateRequest.getPassword()));
        }
        userMapper.update(user, true);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user = userMapper.selectOneById(id);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getUserByOpenId(String openId) {
        User user = userMapper.selectOneByQuery( QueryWrapper.create()
            .and(USER.OPEN_ID.eq(openId)));
        if (Objects.nonNull(user)) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            return userDTO;
        }
        return null;
    }

    @Override
    public UserDTO getCurrentUser() {
        Integer userId = StpUtil.getLoginIdAsInt();
        return getUserById(userId);
    }

    @Override
    public List<UserDTO> all() {
        List<User> userList = userMapper.selectAll();
        List<UserDTO> userDTOList = userList.stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            return userDTO;
        }).collect(java.util.stream.Collectors.toList());
        return userDTOList;
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void logout() {
        StpUtil.logout(StpUtil.getLoginId());
    }
}

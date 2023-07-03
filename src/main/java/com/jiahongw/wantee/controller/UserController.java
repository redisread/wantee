package com.jiahongw.wantee.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jiahongw.wantee.dto.WebBaseResponse;
import com.jiahongw.wantee.dto.user.UserDTO;
import com.jiahongw.wantee.dto.user.UserLoginInfoDTO;
import com.jiahongw.wantee.dto.user.UserLoginRequest;
import com.jiahongw.wantee.dto.user.UserRegisterRequest;
import com.jiahongw.wantee.dto.user.UserUpdateRequest;
import com.jiahongw.wantee.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Redisread
 * @date 2023/5/15
 */
@RestController
@RequestMapping("/api/user")
@Tag(name = "UserController", description = "User管理")
public class UserController {

    @Resource
    private UserService userService;


    @Operation(summary = "注册", description = "register")
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public WebBaseResponse<UserLoginInfoDTO> register(
        @RequestBody UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
        return WebBaseResponse.success();
    }

    @Operation(summary = "登录", description = "login")
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public WebBaseResponse<UserLoginInfoDTO> login(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginInfoDTO userLoginInfoDTO = userService.login(userLoginRequest);
        return WebBaseResponse.success(userLoginInfoDTO);
    }

    @Operation(summary = "登出", description = "logout")
    @PostMapping("/logout")
    @SaCheckLogin
    public WebBaseResponse<UserLoginInfoDTO> logout() {
        userService.logout();
        return WebBaseResponse.success();
    }

    @Operation(summary = "更新", description = "update")
    @PostMapping("/update")
    @SaCheckLogin
    public WebBaseResponse<UserLoginInfoDTO> update(
        @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.update(userUpdateRequest);
        return WebBaseResponse.success();
    }

    @Operation(summary = "查询用户", description = "查询用户")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/get")
    @SaCheckLogin
    public WebBaseResponse<UserDTO> get(@RequestParam("id") Integer id) {
        UserDTO userDTO = userService.getUserById(id);
        return WebBaseResponse.success(userDTO);
    }

    @Operation(summary = "当前用户", description = "当前用户")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/current")
    @SaCheckLogin
    public WebBaseResponse<UserDTO> current() {
        return WebBaseResponse.success(userService.getCurrentUser());
    }

    @Operation(summary = "所有用户", description = "所有用户")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/all")
    @SaCheckLogin
    public WebBaseResponse<List<UserDTO>> all() {
        return WebBaseResponse.success(userService.all());
    }

    @Operation(summary = "删除用户", description = "删除用户")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/delete")
    @SaCheckLogin
    public WebBaseResponse<UserDTO> delete(@RequestParam("id") Integer id) {
        userService.deleteById(id);
        return WebBaseResponse.success();
    }

}

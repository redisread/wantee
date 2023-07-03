package com.jiahongw.wantee.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import com.jiahongw.wantee.common.enums.LoginTypeEnum;
import com.jiahongw.wantee.dto.user.UserDTO;
import com.jiahongw.wantee.service.UserService;
import jakarta.annotation.Resource;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Redisread
 * @date 2023/5/15
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Resource
    private UserService userService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(
            auth -> {
                if (!StpUtil.isLogin()) {
                    String openId = SaHolder.getRequest().getHeader("openId");
                    if (StringUtils.isNotEmpty(openId)) {
                        UserDTO userDTO = userService.getUserByOpenId(openId);
                        if (Objects.nonNull(userDTO)) {
                            StpUtil.login(userDTO.getId(), LoginTypeEnum.API.name());
                        }
                    }
                }
            }

        )).addPathPatterns("/**");
    }


}

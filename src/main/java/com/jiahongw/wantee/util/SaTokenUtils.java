package com.jiahongw.wantee.util;

import cn.dev33.satoken.stp.StpUtil;

/**
 * 鉴权工具类
 *
 * @author Redisread
 * @date 2023/5/17
 */
public class SaTokenUtils {

    public static Integer getLoginId() {
        Object loginId = StpUtil.getLoginId();
        // change to Integer type
        return Integer.parseInt(loginId.toString());
    }

}

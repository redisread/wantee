package com.notekeeper.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Redisread
 * @date 2024/12/8
 */
@Slf4j
@Service
public class BliBliApplicationService {

    /**
     * 我的B站ID
     */
    private static final String MY_BILI_BILI_ID = "404593669";

    /**
     * 模版网址，获取用户关注的UP，最多250
     */
    private static final String GET_USER_FLOWER_URL_TEMPLATE = "https://api.bilibili.com/x/relation/followings?vmid=%s&pn=%s&ps=%s&order_type=desc&jsonp=jsonp";

    /**
     * 获取用户的信息，关注数量等
     */
    private static final String GET_USER_FLOWER_TOTAL_URL_TEMPLATE = "https://api.bilibili.com/x/relation/stat?vmid=%s";

    /**
     * 每页获取的数量
     */
    public static final int PAGE_NUMBER = 50;




}

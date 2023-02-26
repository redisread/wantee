package com.jiahongw.wantee.service;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Lists;
import com.jiahongw.wantee.gateway.HttpClientGateway;
import com.jiahongw.wantee.model.bilibli.UserStatModel;
import com.jiahongw.wantee.util.JsonUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * BliBli API调用服务
 * 参考API地址：https://github.com/SocialSisterYi/bilibili-API-collect
 *
 * @author VictorHong
 * @date 2023/1/26
 */
@Slf4j
@Service
public class BliBliService {

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


    @Resource
    private HttpClientGateway httpClientGateway;

    private int a;

    /**
     * 获取用户的关系状态数
     *
     * @param userId
     * @return
     */
    public Optional<UserStatModel> getUserStat(String userId) {
        String url = String.format(GET_USER_FLOWER_TOTAL_URL_TEMPLATE, userId);
        Optional<String> res = httpClientGateway.simpleGetUrlResult(url);
        if (res.isPresent()) {
            String jsonText = res.get();
            JSONObject userStatStr = JsonUtils.getPropertyJSONObject(jsonText, "data");
            UserStatModel userStatModel = JsonUtils.fromJson(userStatStr.toString(),UserStatModel.class);
            return Optional.of(userStatModel);
        }
        return Optional.empty();
    }

    public void test() {
        Optional<UserStatModel> userStatModelOptional = getUserStat(MY_BILI_BILI_ID);
        UserStatModel userStatModel = userStatModelOptional.get();
        int sum = userStatModel.getFollowing();
        List<String> urlList = Lists.newArrayList();
        for (int i = 1;i < sum / PAGE_NUMBER + 1; ++i) {
            String url = String.format(GET_USER_FLOWER_URL_TEMPLATE, MY_BILI_BILI_ID,i,PAGE_NUMBER);
            urlList.add(url);
        }

        Optional<List<String>> res = httpClientGateway.simpleBatchGetUrlResult(urlList);
        System.out.println(res.get());
    }

    public void a() {
        a = 1;
    }

}

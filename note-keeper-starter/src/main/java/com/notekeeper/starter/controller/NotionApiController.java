package com.notekeeper.starter.controller;

import com.notekeeper.application.service.NotionApplicationService;
import com.notekeeper.starter.controller.response.WebBaseResponse;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Redisread
 * @date 2024/12/8
 */
@Slf4j
@RestController
@RequestMapping("/notion")
public class NotionApiController {

    @Resource
    private NotionApplicationService notionApplicationService;


    /**
     * 创建卡片盒笔记，默认保存为文章类型
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createNotionCardBoxPage")
    public String createNotionCardBoxPage(@RequestBody String request) {
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/querySelectPropertyOptions")
    public WebBaseResponse<List<String>> querySelectPropertyOptionsForCardBoxPage(String propertyName) {
        return null;
    }


}

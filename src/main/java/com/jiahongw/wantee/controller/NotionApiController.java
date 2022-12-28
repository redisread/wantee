package com.jiahongw.wantee.controller;

import com.jiahongw.wantee.controller.request.CreateNotionCardBoxPageRequest;
import com.jiahongw.wantee.service.NotionService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 调用Notion API
 *
 * @author wujiahong06
 * @date 2022/12/28
 */
@Slf4j
@RestController
@RequestMapping("/notion")
public class NotionApiController implements ErrorController {

    @Resource
    private NotionService notionService;

    /**
     * 创建卡片盒笔记，默认保存为文章类型
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createNotionCardBoxPage")
    public String createNotionCardBoxPage(@RequestBody CreateNotionCardBoxPageRequest request) {
        String result = notionService.createNotionCardBoxPageByShortcuts(request.getLink());
        return result;
    }

    /**
     * 测试方法
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getCreateNotionCardBoxPageRequest")
    public CreateNotionCardBoxPageRequest getCreateNotionCardBoxPageRequest(
        @RequestBody CreateNotionCardBoxPageRequest request) {
        return request;
    }
}
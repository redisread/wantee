package com.jiahongw.wantee.service;

import com.jiahongw.wantee.gateway.NotionGateway;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Notion API 服务
 *
 * @author wujiahong06
 * @date 2022/12/28
 */
@Service
public class NotionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotionService.class);

    @Resource
    private NotionGateway notionGateway;

    @Resource
    private WebpageService webpageService;

    /**
     * 通过IOS快捷指令创建Notion卡片盒笔记
     *
     * @param url
     */
    public String createNotionCardBoxPageByShortcuts(String url) {
        String title = webpageService.getWebPageContentTitle(url);
        String createBody = buildCreateBody(url, title);
        return notionGateway.createPage(createBody);
    }

    private String buildCreateBody(String url, String title) {
        String createInfo = "{\"parent\":{\"database_id\":\"%s\"},\"properties\":{\"Name\":{\"title\":[{\"text\":{\"content\":\"%S\"}}]},\"类型\":{\"select\":{\"name\":\"文章\"}},\"卡片类型\":{\"select\":{\"name\":\"文献笔记\"}},\"URL\":{\"url\":\"%s\"}}}";
        String databaseId = "e0e1dd7d81f24ac1915522c02666bda7";
        String createBody = String.format(createInfo, databaseId, title, url);
        return createBody;
    }


}

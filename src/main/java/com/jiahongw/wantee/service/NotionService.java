package com.jiahongw.wantee.service;

import com.jiahongw.wantee.gateway.NotionGateway;
import com.jiahongw.wantee.model.notion.CreatePageResultModel;
import com.jiahongw.wantee.util.JsoupUtils;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Notion API 服务
 *
 * @author wujiahong06
 * @date 2022/12/28
 */
@Slf4j
@Service
public class NotionService {

    @Resource
    private NotionGateway notionGateway;

    private static String UNKNOWN_TITLE = "获取不到标题";

    /**
     * 卡片盒笔记数据库id
     */
    @Value("${notion.infos.card-box-database-id}")
    private String notionCardBoxDatabaseId;

    /**
     * 通过IOS快捷指令创建Notion卡片盒笔记
     *
     * @param url
     */
    public CreatePageResultModel createNotionCardBoxPageByShortcuts(String url) {
        String title = JsoupUtils.getTitleByUrl(url);
        if (StringUtils.isEmpty(title)) {
            title = UNKNOWN_TITLE;
        }
        return createNotionCardBoxPageByShortcuts(url,title);
    }

    public CreatePageResultModel createNotionCardBoxPageByShortcuts(String url, String title) {
        CreatePageResultModel createPageResultModel = new CreatePageResultModel();
        String createBody = buildCreateBody(url, title);
        Response response = notionGateway.createPage(createBody);
        createPageResultModel.setTitle(title);
        createPageResultModel.setHttpStatus(HttpStatus.resolve(response.getStatusCode()));
        return createPageResultModel;
    }

    private String buildCreateBody(String url, String title) {
        String createInfo = "{\"parent\":{\"database_id\":\"%s\"},\"properties\":{\"Name\":{\"title\":[{\"text\":{\"content\":\"%S\"}}]},\"类型\":{\"select\":{\"name\":\"文章\"}},\"卡片类型\":{\"select\":{\"name\":\"文献笔记\"}},\"URL\":{\"url\":\"%s\"}}}";
        String createBody = String.format(createInfo, notionCardBoxDatabaseId, title, url);
        return createBody;
    }


}

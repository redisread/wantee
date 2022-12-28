package com.jiahongw.wantee.gateway;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 访问Notion API 网关层
 *
 * @author wujiahong06
 * @date 2022/12/28
 */
@Component
public class NotionGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotionGateway.class);

    private static final AsyncHttpClient notionClient = new DefaultAsyncHttpClient();

    private static final String NOTION_TOKEN = "Bearer secret_dBdkIIPXAnbM1IAQLNMd9cg6cUbfPXy0wFezX5XEEud";

    /**
     * Notion页面操作URL，包括创建页面、删除页面、更新页面、查询页面
     */
    private static final String NOTION_PAGE_URL = "https://api.notion.com/v1/pages";

    /**
     * 查询页面
     *
     * @param pageId
     * @return
     */
    public String queryPage(String pageId) {
        try {
            Response response = notionClient
                .prepare("GET", NOTION_PAGE_URL + "/" + pageId)
                .setHeader("accept", "application/json")
                .setHeader("Notion-Version", "2022-06-28")
                .setHeader("Authorization", NOTION_TOKEN)
                .execute()
                .toCompletableFuture()
                .get();
            return response.getResponseBody();
        } catch (Exception e) {
            LOGGER.error("queryPage Error,pageId:{}", pageId, e);
            return "";
        }
    }

    /**
     * 更新页面
     *
     * @param pageId     页面id
     * @param updateBody 更新内容
     * @return
     */
    public String updatePage(String pageId, String updateBody) {
        try {
            Response response = notionClient
                .prepare("PATCH", NOTION_PAGE_URL + "/" + pageId)
                .setHeader("accept", "application/json")
                .setHeader("Notion-Version", "2022-06-28")
                .setHeader("content-type", "application/json")
                .setHeader("Authorization", NOTION_TOKEN)
                .setBody(updateBody)
                .execute()
                .get();
            return response.getResponseBody();
        } catch (Exception e) {
            LOGGER.error("updatePage Error,pageId:{}", pageId, e);
            return "";
        }
    }

    /**
     * 创建页面
     *
     * @param createBody 创建内容
     * @return
     */
    public String createPage(String createBody) {
        try {
            Response response = notionClient
                .prepare("POST", NOTION_PAGE_URL)
                .setHeader("accept", "application/json")
                .setHeader("Notion-Version", "2022-06-28")
                .setHeader("content-type", "application/json")
                .setHeader("Authorization", NOTION_TOKEN)
                .setBody(createBody)
                .execute()
                .get();
            return response.getResponseBody();
        } catch (Exception e) {
            LOGGER.error("createPage Error,createBody:{}", createBody, e);
        }
        return "";
    }


}

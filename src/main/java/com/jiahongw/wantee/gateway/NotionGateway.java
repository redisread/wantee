package com.jiahongw.wantee.gateway;

import com.jiahongw.wantee.constant.enums.BizExceptionCodeEnum;
import com.jiahongw.wantee.exception.BizException;
import java.util.Objects;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
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

    /**
     * Notion api 访问token
     */
    @Value("${notion.infos.token}")
    private String notionApiToken;

    /**
     * 卡片盒笔记数据库id
     */
    @Value("${notion.infos.card-box-database-id}")
    private String notionCardBoxDatabaseId;

    /**
     * Notion页面操作URL，包括创建页面、删除页面、更新页面、查询页面
     */
    @Value("${notion.infos.page-url}")
    private String notionPageUrl;

    /**
     * Notion数据库操作URL
     */
    @Value("${notion.infos.database-url}")
    private String notionDatabaseUrl;

    /**
     * Notion数据库操作URL
     */
    @Value("${notion.infos.version}")
    private String notionVersion;

    /**
     * 查询页面
     *
     * @param pageId
     * @return
     */
    public String queryPage(String pageId) {
        try {
            Response response = notionClient
                .prepare("GET", notionPageUrl + "/" + pageId)
                .setHeader("accept", "application/json")
                .setHeader("Notion-Version", notionVersion)
                .setHeader("Authorization", notionApiToken)
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
                .prepare("PATCH", notionPageUrl + "/" + pageId)
                .setHeader("accept", "application/json")
                .setHeader("Notion-Version", notionVersion)
                .setHeader("content-type", "application/json")
                .setHeader("Authorization", notionApiToken)
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
    @Retryable(value = BizException.class, maxAttempts = 2,
        backoff= @Backoff(value = 1500, maxDelay = 100000, multiplier = 1.2))
    public Response createPage(String createBody) {
        try {
            Response response = notionClient
                .prepare("POST", notionPageUrl)
                .setHeader("accept", "application/json")
                .setHeader("Notion-Version", notionVersion)
                .setHeader("content-type", "application/json")
                .setHeader("Authorization", notionApiToken)
                .setBody(createBody)
                .execute()
                .get();
            if (Objects.isNull(response) || response.getStatusCode() != HttpStatus.OK.value()) {
                throw new BizException(BizExceptionCodeEnum.CREATE_NOTION_PAGE_EXCEPTION);
            }
            return response;
        } catch (Exception e) {
            LOGGER.error("createPage Error,createBody:{}", createBody, e);
            throw new BizException(BizExceptionCodeEnum.CREATE_NOTION_PAGE_EXCEPTION);
        }
    }

    /**
     * 查询数据库信息
     *
     * @param databaseId 数据库id
     * @return
     */
    public String queryDatabase(String databaseId) {
        try {
            Response response = notionClient
                .prepare("GET", notionDatabaseUrl + "/" + databaseId)
                .setHeader("accept", "application/json")
                .setHeader("Notion-Version", notionVersion)
                .setHeader("Authorization", notionApiToken)
                .execute()
                .get();
            return response.getResponseBody();
        } catch (Exception e) {
            LOGGER.error("queryDatabase Error,databaseId:{}", databaseId, e);
        }
        return "";
    }


}

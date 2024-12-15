package com.notekeeper.application.gateway;

/**
 * @author Redisread
 * @date 2024/12/8
 */
public interface NotionGateway {

    String queryPage(String notionApiToken, String databaseId, String pageId);

    String queryDatabase(String notionApiToken, String databaseId);
}

package com.notekeeper.infrastructure.gateway;

import com.notekeeper.application.gateway.NotionGateway;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.stereotype.Component;

/**
 * @author Redisread
 * @date 2024/12/8
 */
@Component
@Slf4j
public class NotionGatewayImpl implements NotionGateway {

    private static final AsyncHttpClient notionClient = new DefaultAsyncHttpClient();


    @Override
    public String queryPage(String notionApiToken, String databaseId, String pageId) {

        return null;
    }

    @Override
    public String queryDatabase(String notionApiToken, String databaseId) {
        return null;
    }
}

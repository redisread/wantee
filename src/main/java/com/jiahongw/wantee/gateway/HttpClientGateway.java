package com.jiahongw.wantee.gateway;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.stereotype.Component;

/**
 * 访问HTTP请求
 *
 * @author VictorHong
 * @date 2023/1/26
 */
@Slf4j
@Component
public class HttpClientGateway {

    /**
     * get 调用 url 获取返回结果
     *
     * @param url
     * @return
     */
    public Optional<String> simpleGetUrlResult(String url) {
        try (AsyncHttpClient notionClient = new DefaultAsyncHttpClient()) {
            Response response = notionClient
                .prepare("GET", url)
                .setHeader("accept", "application/json")
                .execute()
                .toCompletableFuture()
                .get();
            return Optional.of(response.getResponseBody());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("simpleGetUrlResult error", e);
        }
        return Optional.empty();
    }

    public Optional<List<String>> simpleBatchGetUrlResult(List<String> urlList) {
        try (AsyncHttpClient notionClient = new DefaultAsyncHttpClient()) {
            List<String> result = Lists.newArrayList();
            for (String url : urlList) {
                Response response = notionClient
                    .prepare("GET", url)
                    .setHeader("accept", "application/json")
                    .execute()
                    .toCompletableFuture()
                    .get();
                result.add(response.getResponseBody());
            }
            return Optional.of(result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("simpleBatchGetUrlResult error", e);
        }
        return Optional.empty();
    }

}

package com.jiahongw.wantee.gateway;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 访问网页信息网关
 *
 * @author wujiahong06
 * @date 2022/12/28
 */
@Component
public class WebpageGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebpageGateway.class);

    public String getContentByUrl(String url) {
        try {
            try (AsyncHttpClient client = new DefaultAsyncHttpClient()) {
                return client
                    .prepareGet(url)
                    .execute()
                    .get()
                    .getResponseBody();
            }
        } catch (Exception e) {
            LOGGER.error("getContentByUrl error", e);
        }
        return "";
    }

}

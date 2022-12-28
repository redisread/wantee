package com.jiahongw.wantee.service;

import com.jiahongw.wantee.gateway.WebpageGateway;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 查询网页内容服务
 *
 * @author wujiahong06
 * @date 2022/12/28
 */
@Service
public class WebpageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebpageService.class);

    private static final String GET_URL_TITLE_PATTERN = "(<title>|<TITLE>)(.*?)(</title>|</TITLE>)";

    @Resource
    private WebpageGateway webpageGateway;

    public String getWebPageContentTitle(String url) {
        try {
            String content = webpageGateway.getContentByUrl(url);
            Pattern r = Pattern.compile(GET_URL_TITLE_PATTERN);
            Matcher m = r.matcher(content);

            if (m.find()) {
                return m.group(2).toString();
            }
        } catch (Exception e) {
            LOGGER.error("getWebPageContentTitle error, url:{}", url, e);
            return "解析网页标题错误";
        }
        LOGGER.warn("getWebPageContentTitle 找不到网页标题, url :{}", url);
        return "找不到网页标题";
    }

}

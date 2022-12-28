package com.jiahongw.wantee.service;

import com.jiahongw.wantee.gateway.WebpageGateway;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
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

    private static final Pattern NORMAL_PAGE_TITLE_PATTERN = Pattern
        .compile("(<title>|<TITLE>)(.*?)(</title>|</TITLE>)", Pattern.DOTALL);

    private static final Pattern WX_PAGE_TITLE_PATTERN = Pattern
        .compile("<.*?rich_media_title.*?>(.*?)<.*?>", Pattern.DOTALL);

    @Resource
    private WebpageGateway webpageGateway;

    public String getWebPageContentTitle(String url) {
        try {
            String content = webpageGateway.getContentByUrl(url);
            Matcher normalMatcher = NORMAL_PAGE_TITLE_PATTERN.matcher(content);
            String title = "";
            // 普通文章
            if (normalMatcher.find()) {
                title = normalMatcher.group(2).trim();
                if (StringUtils.isNotEmpty(title)) {
                    return title;
                }
            }
            // 微信文章解析title
            Matcher wxMatcher = WX_PAGE_TITLE_PATTERN.matcher(content);
            if (wxMatcher.find()) {
                title = wxMatcher.group(1).trim();
                if (StringUtils.isNotEmpty(title)) {
                    return title;
                }
            }
        } catch (Exception e) {
            LOGGER.error("getWebPageContentTitle error, url:{}", url, e);
            return "解析网页标题错误";
        }
        LOGGER.warn("getWebPageContentTitle 找不到网页标题, url :{}", url);
        return "找不到网页标题";
    }

}

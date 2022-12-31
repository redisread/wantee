package com.jiahongw.wantee.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author VictorHong
 * @date 2022/12/31
 */
@Slf4j
public class JsoupUtils {

    /**
     * 获取网页的标题
     *
     * @param url 网址
     * @return 标题
     */
    public static String getTitleByUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.title();
        } catch (Exception e) {
            log.error("获取标题失败", e);
        }
        return null;
    }


}

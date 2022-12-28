package com.jiahongw.wantee.service

import com.jiahongw.wantee.BasicSpec
import org.apache.commons.lang3.StringUtils

import javax.annotation.Resource

class WebpageServiceSpecIT extends BasicSpec {

    @Resource
    private WebpageService webpageService;

    def "test get Title"() {
        when:
        String title = webpageService.getWebPageContentTitle(url)
        then:
        logger.info("title:{}", title)
        StringUtils.isNotEmpty(title)
        where:
        desc   | url
        "微信文章" | "https://mp.weixin.qq.com/s/0RWaupDaqSJulBTV_3v5Mw"
        "CSDN" | "https://blog.csdn.net/qq_52797170/article/details/125591705"
    }
}
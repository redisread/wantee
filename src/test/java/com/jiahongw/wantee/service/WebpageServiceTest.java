package com.jiahongw.wantee.service;

import com.jiahongw.wantee.BaseTest;
import javax.annotation.Resource;
import org.junit.Test;

public class WebpageServiceTest extends BaseTest {

    @Resource
    private WebpageService webpageService;

    @Test
    public void testGetTitle() {
        String url = "https://blog.51cto.com/zhaoyanjun/3814791";
        String title = webpageService.getWebPageContentTitle(url);
        System.out.println(title);
    }
}

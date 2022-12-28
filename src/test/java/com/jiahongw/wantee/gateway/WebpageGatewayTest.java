package com.jiahongw.wantee.gateway;

import com.jiahongw.wantee.BaseTest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;

class WebpageGatewayTest extends BaseTest {

    @Resource
    private WebpageGateway webpageGateway;

    @Test
    public void testGetContentByUrl() {
        String url = "https://blog.51cto.com/zhaoyanjun/3814791";
        String result = webpageGateway.getContentByUrl(url);

        System.out.println(result);

        String pattern = "(<title>|<TITLE>)(.*?)(</title>|</TITLE>)";
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(result);

        if (m.find()) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(m.group(1));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(m.group(2));
        } else {
            System.out.println("NOOOOOOOOO");
        }
    }

}
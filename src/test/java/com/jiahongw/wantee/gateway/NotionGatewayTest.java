package com.jiahongw.wantee.gateway;

import com.jiahongw.wantee.BaseTest;
import com.jiahongw.wantee.model.NotionPage;
import com.jiahongw.wantee.util.JsonUtils;
import javax.annotation.Resource;
import org.junit.Test;

public class NotionGatewayTest extends BaseTest {

    @Resource
    private NotionGateway notionGateway;

    @Test
    public void testQueryPage() {
        String pageId = "c866a45c96b049488c87c37f1e0d73dd";
        String result = notionGateway.queryPage(pageId);
        NotionPage notionPage = JsonUtils.fromJson(result, NotionPage.class);
        System.out.println(result);
        System.out.println(notionPage);
    }
//
//    @Test
//    public void testUpdatePage() {
//        String pageId = "dceadb4874104033bec1767756b09ebb";
//        String updateBody = "{\"properties\":{\"类型\":{\"select\":{\"name\":\"视频\"}}}}";
//        String result = notionGateway.updatePage(pageId, updateBody);
//        System.out.println(result);
//    }
//
//    @Test
//    public void testCreatePage() {
//        String title = "测试1";
//        String databaseId = "e0e1dd7d81f24ac1915522c02666bda7";
//        String createInfo = "{\"parent\":{\"database_id\":\"%s\"},\"properties\":{\"Name\":{\"title\":[{\"text\":{\"content\":\"%S\"}}]},\"类型\":{\"select\":{\"name\":\"文章\"}},\"卡片类型\":{\"select\":{\"name\":\"文献笔记\"}},\"URL\":{\"url\":\"%s\"}}}";
//        String url = "https://baidu.com";
//        String createBody = String.format(createInfo, databaseId, title, url);
//        Response result = notionGateway.createPage(createBody);
//        System.out.println(result);
//    }
//
//    @Test
//    public void testQueryDatabase() {
//        String databaseId = "e0e1dd7d81f24ac1915522c02666bda7";
//        String result = notionGateway.queryDatabase(databaseId);
//        NotionCardBoxDatabaseModel notionCardBoxDatabaseModel = JsonUtils
//            .fromJson(result, NotionCardBoxDatabaseModel.class);
//        List<String> typeList = notionCardBoxDatabaseModel
//            .getProperties()
//            .getCardTypeDTO()
//            .getSelect()
//            .getOptions()
//            .stream()
//            .map(OptionsDTO::getName).collect(Collectors.toList());
//        System.out.println(result);
//        System.out.println("typeList: " + typeList);
//
//    }


}

package com.jiahongw.wantee.service;

import com.jiahongw.wantee.controller.request.CreateNotionCardBoxPageRequest;
import com.jiahongw.wantee.gateway.NotionGateway;
import com.jiahongw.wantee.model.notion.CreateCardBoxPageModel;
import com.jiahongw.wantee.model.notion.CreatePageResultModel;
import com.jiahongw.wantee.model.notion.OptionModel;
import com.jiahongw.wantee.model.notion.SelectModel;
import com.jiahongw.wantee.util.JsoupUtils;
import com.jiahongw.wantee.util.NotionParseUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Notion API 服务
 *
 * @author wujiahong06
 * @date 2022/12/28
 */
@Slf4j
@Service
public class NotionService {

    @Resource
    private NotionGateway notionGateway;

    private static String UNKNOWN_TITLE = "获取不到标题";

    /**
     * 卡片盒笔记数据库id
     */
    @Value("${notion.infos.card-box-database-id}")
    private String notionCardBoxDatabaseId;

    private static String CARD_BOX_PAGE_CREATE_INFO = "{\"parent\":{\"database_id\":\"%s\"},\"properties\":{\"Name\":{\"title\":[{\"text\":{\"content\":\"%s\"}}]},\"类型\":{\"select\":{\"name\":\"%s\"}},\"卡片类型\":{\"select\":{\"name\":\"文献笔记\"}},\"URL\":{\"url\":\"%s\"}}}";

    /**
     * 通过IOS快捷指令创建Notion卡片盒笔记
     */
    public CreatePageResultModel createNotionCardBoxPageByShortcuts(
        CreateNotionCardBoxPageRequest cardBoxPageRequest) {
        CreatePageResultModel createPageResultModel = new CreatePageResultModel();
        CreateCardBoxPageModel createCardBoxPageModel = buildCreateCardBoxPageModel(
            cardBoxPageRequest);
        String createBody = String
            .format(CARD_BOX_PAGE_CREATE_INFO, notionCardBoxDatabaseId,
                createCardBoxPageModel.getTitle(), createCardBoxPageModel.getType(),
                createCardBoxPageModel.getUrl());
        Response response = notionGateway.createPage(createBody);
        createPageResultModel.setTitle(createCardBoxPageModel.getTitle());
        createPageResultModel.setHttpStatus(HttpStatus.resolve(response.getStatusCode()));
        return createPageResultModel;
    }

    private CreateCardBoxPageModel buildCreateCardBoxPageModel(
        CreateNotionCardBoxPageRequest request) {
        CreateCardBoxPageModel createCardBoxPageModel = new CreateCardBoxPageModel();
        String title = JsoupUtils.getTitleByUrl(request.getLink());
        String type = StringUtils.isNotEmpty(request.getType()) ? request.getType() : "文章";
        createCardBoxPageModel.setUrl(request.getLink());
        createCardBoxPageModel.setTitle(title);
        createCardBoxPageModel.setType(type);
        return createCardBoxPageModel;
    }

    /**
     * 获取选择属性名称的列表
     *
     * @param propertyName
     * @return
     */
    public List<String> queryNotionCardBoxSelectPropertyNames(String propertyName) {
        String databaseJsonText = notionGateway.queryDatabase(notionCardBoxDatabaseId);
        SelectModel selectModel = NotionParseUtils
            .getSelectModelByPropertyName(databaseJsonText, propertyName);
        if (Objects.isNull(selectModel) || Objects.isNull(selectModel.getOptions())) {
            return Collections.emptyList();
        }
        return selectModel.getOptions()
            .stream()
            .map(OptionModel::getName)
            .distinct()
            .collect(Collectors.toList());
    }
}

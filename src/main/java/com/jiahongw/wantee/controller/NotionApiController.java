package com.jiahongw.wantee.controller;

import com.jiahongw.wantee.controller.request.CreateNotionCardBoxPageRequest;
import com.jiahongw.wantee.controller.response.WebBaseResponse;
import com.jiahongw.wantee.model.notion.CreatePageResultModel;
import com.jiahongw.wantee.service.NotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 调用Notion API
 *
 * @author wujiahong06
 * @date 2022/12/28
 */
@Slf4j
@Tag(name = "NotionApiController", description = "Notion管理")
@RestController
@RequestMapping("/notion")
public class NotionApiController implements ErrorController {

    @Resource
    private NotionService notionService;

    /**
     * 创建卡片盒笔记，默认保存为文章类型
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建卡片盒笔记",description = "创建卡片盒笔记，需要token")
    @RequestMapping(method = RequestMethod.POST, value = "/createNotionCardBoxPage")
    public String createNotionCardBoxPage(@RequestBody CreateNotionCardBoxPageRequest request) {
        try {
            CreatePageResultModel createPageResultModel = notionService
                .createNotionCardBoxPageByShortcuts(request);
            if (Objects.nonNull(createPageResultModel)) {
                HttpStatus httpStatus = createPageResultModel.getHttpStatus();
                if (Objects.nonNull(httpStatus)) {
                    if (HttpStatus.OK.value() == httpStatus.value()) {
                        return String.format("保存文章[%s]成功", createPageResultModel.getTitle());
                    }
                    if (StringUtils.isNotEmpty(httpStatus.getReasonPhrase())) {
                        return httpStatus.getReasonPhrase();
                    }
                }
            }
        } catch (Exception e) {
            log.error("createNotionCardBoxPage error",e);
            return e.getMessage();
        }
        return "UNKNOWN";
    }

    /**
     * 通过属性名查询卡片盒笔记下的选择属性名列表
     *
     * @param propertyName 属性名
     * @return
     */
    @Operation(summary = "查询属性列表",description = "查询属性列表")
    @RequestMapping(method = RequestMethod.GET, value = "/querySelectPropertyOptions")
    public WebBaseResponse<List<String>> querySelectPropertyOptionsForCardBoxPage(String propertyName) {
        List<String> names = notionService.queryNotionCardBoxSelectPropertyNames(propertyName);
        return WebBaseResponse.success(names);
    }

    /**
     * 测试方法
     *
     * @param request
     * @return
     */
    @Operation(summary = "测试",description = "测试")
    @RequestMapping(method = RequestMethod.POST, value = "/getCreateNotionCardBoxPageRequest")
    public CreateNotionCardBoxPageRequest getCreateNotionCardBoxPageRequest(
        @RequestBody CreateNotionCardBoxPageRequest request) {
        return request;
    }
}

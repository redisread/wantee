package com.jiahongw.wantee.controller;

import com.jiahongw.wantee.controller.request.CreateNotionCardBoxPageRequest;
import com.jiahongw.wantee.controller.response.WebBaseResponse;
import com.jiahongw.wantee.model.notion.CreatePageResultModel;
import com.jiahongw.wantee.service.NotionService;
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
    @RequestMapping(method = RequestMethod.POST, value = "/getCreateNotionCardBoxPageRequest")
    public CreateNotionCardBoxPageRequest getCreateNotionCardBoxPageRequest(
        @RequestBody CreateNotionCardBoxPageRequest request) {
        return request;
    }
}

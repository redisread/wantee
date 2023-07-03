package com.jiahongw.wantee.controller;

import cn.dev33.satoken.annotation.SaCheckBasic;
import cn.dev33.satoken.stp.StpUtil;
import com.jiahongw.wantee.dto.WebBaseResponse;
import com.jiahongw.wantee.dto.tag.CreateTagRequest;
import com.jiahongw.wantee.dto.tag.DeleteTagRequest;
import com.jiahongw.wantee.dto.tag.ListTagRequest;
import com.jiahongw.wantee.dto.tag.TagDTO;
import com.jiahongw.wantee.dto.tag.UpdateTagRequest;
import com.jiahongw.wantee.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Redisread
 * @date 2023/5/20
 */
@RestController
@RequestMapping("/api/tag")
@Tag(name = "TagController", description = "Tag管理")
public class TagController {

    @Resource
    private TagService tagService;


    @Operation(summary = "查询Tag列表", description = "查询Tag列表")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/list")
    public WebBaseResponse<List<TagDTO>> list(
        @RequestBody ListTagRequest listTagRequest) {
//        StpUtil.get
        Integer userId = StpUtil.getLoginIdAsInt();
        return WebBaseResponse.success(tagService.listByUser(userId));
    }


    @Operation(summary = "创建标签", description = "创建标签")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/create")
    public WebBaseResponse<Void> create(
        @RequestBody CreateTagRequest createTagRequest) {
        Integer userId = StpUtil.getLoginIdAsInt();
        tagService.create(userId,createTagRequest.getTagList());
        return WebBaseResponse.success();
    }


    @Operation(summary = "删除标签", description = "删除标签")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/delete")
    public WebBaseResponse<Void> delete(
        @RequestBody DeleteTagRequest deleteTagRequest) {
        Integer userId = StpUtil.getLoginIdAsInt();
        tagService.delete(userId,deleteTagRequest.getTagNameList());
        return WebBaseResponse.success();
    }

    @Operation(summary = "更新标签", description = "更新标签")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/update")
    public WebBaseResponse<Void> update(
        @RequestBody UpdateTagRequest updateTagRequest) {
        tagService.update(updateTagRequest.getTagId2NameMap());
        return WebBaseResponse.success();
    }


}

package com.jiahongw.wantee.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jiahongw.wantee.dto.WebBaseResponse;
import com.jiahongw.wantee.dto.resource.UploadResourceResponse;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Redisread
 * @date 2023/5/14
 */
@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @PostMapping("/upload")
    @SaCheckLogin
    public WebBaseResponse<List<UploadResourceResponse>> upload(
        @RequestParam("files") MultipartFile[] files) {
        return WebBaseResponse.build(2, "null", null);
    }
}

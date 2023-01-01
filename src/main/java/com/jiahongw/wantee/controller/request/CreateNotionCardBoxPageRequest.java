package com.jiahongw.wantee.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author wujiahong06
 * @date 2022/12/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotionCardBoxPageRequest {

    /**
     * 网页链接
     */
    @NonNull
    private String link;

    /**
     * 页面标题
     */
    private String title;

    /**
     * 类型，视频、文章、书籍、想法、音频等
     */
    private String type;
}

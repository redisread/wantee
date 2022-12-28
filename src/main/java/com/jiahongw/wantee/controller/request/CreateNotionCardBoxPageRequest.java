package com.jiahongw.wantee.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String link;
}

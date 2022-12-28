package com.jiahongw.wantee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Notion 卡片盒页面信息
 *
 * @author wujiahong06
 * @date 2022/12/28
 */
@NoArgsConstructor
@Data
public class NotionPage {

    @JsonProperty("object")
    private String object;
    @JsonProperty("id")
    private String id;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("last_edited_time")
    private String lastEditedTime;
    @JsonProperty("created_by")
    private OperatorDTO createdBy;
    @JsonProperty("last_edited_by")
    private OperatorDTO lastEditedBy;
    /**
     * 展示图
     */
    @JsonProperty("cover")
    private IconDTO cover;
    @JsonProperty("icon")
    private IconDTO icon;

    @JsonProperty("parent")
    private ParentDTO parent;
    @JsonProperty("archived")
    private Boolean archived;

    @JsonProperty("url")
    private String url;

    @NoArgsConstructor
    @Data
    public static class OperatorDTO {

        @JsonProperty("object")
        private String object;
        @JsonProperty("id")
        private String id;
    }

    @NoArgsConstructor
    @Data
    public static class IconDTO {

        @JsonProperty("type")
        private String type;
        @JsonProperty("external")
        private ExternalDTO external;

        @NoArgsConstructor
        @Data
        public static class ExternalDTO {

            @JsonProperty("url")
            private String url;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ParentDTO {

        @JsonProperty("type")
        private String type;
        @JsonProperty("database_id")
        private String databaseId;
    }
}

package com.jiahongw.wantee.dto.note;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NoteDTO {

    private Integer id;

    private String content;

    private List<String> tagList;

    private List<ResourceItem> resourcesList;

    private String visibility;

    private String status;

    private Integer userId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Data
    public static class ResourceItem {

        private String publicId;
        private String url;
        private String fileType;
        private String suffix;
    }
}

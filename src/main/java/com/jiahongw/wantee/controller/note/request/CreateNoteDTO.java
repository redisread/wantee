package com.jiahongw.wantee.controller.note.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VictorHong
 * @date 2023/4/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "创建笔记请求")
public class CreateNoteDTO {

    @Schema(description = "笔记内容")
    @NotBlank(message = "笔记内容不能为空")
    private String content;

    @Schema(description = "创建人")
    private Long creatorId;
}

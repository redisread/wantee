package com.jiahongw.wantee.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.jiahongw.wantee.dto.WebBaseResponse;
import com.jiahongw.wantee.dto.note.ListNoteRequest;
import com.jiahongw.wantee.dto.note.NoteDTO;
import com.jiahongw.wantee.dto.note.UpdateNoteRequest;
import com.jiahongw.wantee.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Redisread
 * @date 2023/5/14
 */
@Slf4j
@Tag(name = "NoteController", description = "Note管理")
@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Resource
    private NoteService noteService;

    @Operation(summary = "创建笔记", description = "创建笔记")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    @SaCheckLogin
    public WebBaseResponse<NoteDTO> create(@RequestBody UpdateNoteRequest createNoteRequest) {
        if (Objects.isNull(createNoteRequest.getUserId())) {
            createNoteRequest.setUserId(StpUtil.getLoginIdAsInt());
        }
        noteService.create(createNoteRequest);
        return WebBaseResponse.success( null);
    }

    @Operation(summary = "更新笔记", description = "更新笔记")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public WebBaseResponse<NoteDTO> update(@RequestBody UpdateNoteRequest updateNoteRequest) {
        if (Objects.isNull(updateNoteRequest.getUserId())) {
            updateNoteRequest.setUserId(StpUtil.getLoginIdAsInt());
        }
        noteService.update(updateNoteRequest);
        return WebBaseResponse.success(null);
    }

    @Operation(summary = "归档笔记", description = "归档笔记")
    @PostMapping("/archive")
    public WebBaseResponse<Void> archive(@RequestParam("id") int id) {
        noteService.archive(id);
        return WebBaseResponse.success(null);
    }

    @Operation(summary = "删除笔记", description = "删除笔记")
    @PostMapping("/delete")
    public WebBaseResponse<Void> delete(@RequestParam("id") int id) {
        noteService.delete(id);
        return WebBaseResponse.success(null);
    }

    @Operation(summary = "获取笔记", description = "获取笔记")
    @PostMapping("/{id}")
    public WebBaseResponse<NoteDTO> get(@PathVariable("id") int id) {
        return WebBaseResponse.success(noteService.get(id));
    }

    @PostMapping("/list")
    public WebBaseResponse<NoteDTO> list(@RequestBody @Validated ListNoteRequest listNoteRequest) {
        return WebBaseResponse.build(2, "null", null);
    }

    @PostMapping("/all")
    public WebBaseResponse<List<NoteDTO>> all() {
        Integer userId = StpUtil.getLoginIdAsInt();
        return WebBaseResponse.success(noteService.all(userId));
    }

}

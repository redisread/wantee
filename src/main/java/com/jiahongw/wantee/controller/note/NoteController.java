package com.jiahongw.wantee.controller.note;

import com.jiahongw.wantee.controller.note.request.CreateNoteDTO;
import com.jiahongw.wantee.controller.response.WebBaseResponse;
import com.jiahongw.wantee.model.note.NoteModel;
import com.jiahongw.wantee.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author VictorHong
 * @date 2023/4/15
 */
@Slf4j
@Tag(name = "NoteController", description = "Note管理")
@RestController
@RequestMapping("/note")
public class NoteController {

    @Resource
    private NoteService noteService;

    @Operation(summary = "创建笔记", description = "创建笔记")
    @RequestMapping(method = RequestMethod.POST, value = "/createNote")
    public WebBaseResponse<NoteModel> createNote(@RequestBody CreateNoteDTO createNoteDTO) {
        try {
            Optional<NoteModel> noteModel = noteService.createNote(createNoteDTO);
            if (noteModel.isPresent()) {
                return WebBaseResponse.success(noteModel.get());
            }
        } catch (Exception e) {
            log.error("createNote error", e);
            return WebBaseResponse.error(-1, e.getMessage());
        }
        return WebBaseResponse.build(2, "null", null);
    }
}

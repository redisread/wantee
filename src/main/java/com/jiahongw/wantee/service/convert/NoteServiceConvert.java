package com.jiahongw.wantee.service.convert;

import com.jiahongw.wantee.controller.note.request.CreateNoteDTO;
import com.jiahongw.wantee.entity.NotePO;
import com.jiahongw.wantee.model.note.NoteModel;
import com.jiahongw.wantee.util.JsonUtils;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * @author VictorHong
 * @date 2023/4/15
 */
public class NoteServiceConvert {

    public static NotePO convert2NotePO(CreateNoteDTO createNoteDTO, List<Long> tagList) {
        if (Objects.isNull(createNoteDTO)) {
            return null;
        }
        NotePO notePO = new NotePO();
        notePO.setTagIdList(JsonUtils.toJson(tagList));
        notePO.setStatus(Byte.valueOf("1"));
        notePO.setCreatorId(createNoteDTO.getCreatorId());
        notePO.setCreateTime(new Date());
        notePO.setUpdaterId(createNoteDTO.getCreatorId());
        notePO.setUpdateTime(new Date());
        notePO.setContent(createNoteDTO.getContent());
        return notePO;
    }

    public static NoteModel convert2NoteModel(NotePO notePO) {
        NoteModel noteModel = new NoteModel();
        noteModel.setId(notePO.getId());
        noteModel.setStatus(notePO.getStatus().intValue());
        if (StringUtils.isNotEmpty(notePO.getTagIdList())) {
            noteModel.setTagIdList(JsonUtils.parseString2List(notePO.getTagIdList(), Long.class));
        }
        if (StringUtils.isNotEmpty(notePO.getResourceIdList())) {
            noteModel.setResourceIdList(
                JsonUtils.parseString2List(notePO.getResourceIdList(), Long.class));
        }
        noteModel.setCreatorId(notePO.getCreatorId());
        LocalDateTime createTime = LocalDateTime
            .ofInstant(notePO.getCreateTime().toInstant(), ZoneId.systemDefault());
        noteModel.setCreateTime(createTime);
        noteModel.setUpdaterId(notePO.getUpdaterId());
        LocalDateTime updateTime = LocalDateTime
            .ofInstant(notePO.getUpdateTime().toInstant(), ZoneId.systemDefault());
        noteModel.setUpdateTime(updateTime);
        return noteModel;
    }
}

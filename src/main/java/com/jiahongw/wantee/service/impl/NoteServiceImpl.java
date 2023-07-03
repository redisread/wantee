package com.jiahongw.wantee.service.impl;

import static com.jiahongw.wantee.entity.table.Tables.NOTE;
import static com.jiahongw.wantee.entity.table.Tables.TAG;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jiahongw.wantee.common.enums.NoteStatusEnum;
import com.jiahongw.wantee.dto.note.ListNoteRequest;
import com.jiahongw.wantee.dto.note.NoteDTO;
import com.jiahongw.wantee.dto.note.UpdateNoteRequest;
import com.jiahongw.wantee.dto.tag.TagDTO;
import com.jiahongw.wantee.entity.Note;
import com.jiahongw.wantee.mapper.NoteMapperExt;
import com.jiahongw.wantee.service.NoteService;
import com.jiahongw.wantee.service.TagService;
import com.jiahongw.wantee.util.JsonUtils;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author VictorHong
 * @date 2023/4/15
 */
@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Resource
    private NoteMapperExt noteMapper;

    @Resource
    private TagService tagService;

    private Set<String> parseTagList(String content) {
        // 定义正则表达式
        String regex = "#(\\S+)";

        // 创建Pattern对象
        Pattern pattern = Pattern.compile(regex);

        // 创建Matcher对象
        Matcher matcher = pattern.matcher(content);

        Set<String> tagNameSet = Sets.newHashSet();

        // 查找所有匹配的标签
        while (matcher.find()) {
            String tag = matcher.group(1);
            tagNameSet.add(tag);
        }
        return tagNameSet;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(UpdateNoteRequest createRequest) {
        Note note = getNoteByUpdateNoteRequest(createRequest);
        noteMapper.insertSelective(note);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UpdateNoteRequest updateNoteRequest) {
        Note note = getNoteByUpdateNoteRequest(updateNoteRequest);
        noteMapper.update(note);
    }

    private Note getNoteByUpdateNoteRequest(UpdateNoteRequest updateNoteRequest) {
        String content = updateNoteRequest.getContent();
        Set<String> tagSet = parseTagList(content);
        Integer userId = updateNoteRequest.getUserId();
        Note note = new Note();
        note.setUserId(userId);
        note.setContent(content);
        note.setTags(StringUtils.joinWith(",", tagSet));
        note.setId(updateNoteRequest.getId());
        if (updateNoteRequest.getVisibility() != null) {
            note.setVisibility(updateNoteRequest.getVisibility().name());
        }
        checkCreateTags(userId, tagSet);
        return note;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void archive(Integer noteId) {
        Note note = new Note();
        note.setId(noteId);
        note.setStatus(NoteStatusEnum.ARCHIVED.name());
        noteMapper.update(note);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void normal(Integer noteId) {
        Note note = new Note();
        note.setId(noteId);
        note.setStatus(NoteStatusEnum.NORMAL.name());
        noteMapper.update(note);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer noteId) {
        noteMapper.deleteById(noteId);
    }

    @Override
    public NoteDTO get(Integer noteId) {
        Note note = noteMapper.selectOneById(noteId);
        if (Objects.isNull(note)) {
            return null;
        }
        NoteDTO noteDTO = new NoteDTO();
        BeanUtils.copyProperties(note, noteDTO);
        if (StringUtils.isNotEmpty(note.getTags())) {
            noteDTO.setTagList(null);
        }
        return null;
    }

    @Override
    public List<NoteDTO> list(ListNoteRequest listNoteRequest) {
        return null;
    }

    @Override
    public List<NoteDTO> all(Integer userId) {
        QueryWrapper query = QueryWrapper.create()
            .select()
            .from(NOTE)
            .where(NOTE.USER_ID.eq(userId));
        List<Note> allNoteList = noteMapper.selectListByQuery(query);
        if (CollectionUtils.isEmpty(allNoteList)) {
            return Collections.emptyList();
        }
        List<NoteDTO> noteDTOList = allNoteList.stream().map(note -> {
            NoteDTO noteDTO = new NoteDTO();
            BeanUtils.copyProperties(note, noteDTO);
            if (StringUtils.isNotEmpty(note.getTags())) {
                List<String> tags = JsonUtils.parseString2List(note.getTags(),String.class);
                noteDTO.setTagList(tags);
            }
            return noteDTO;
        }).collect(Collectors.toList());
        return noteDTOList;
    }


    private void checkCreateTags(Integer userId, Set<String> tagSet) {
        List<TagDTO> tagDTOList = tagService.listByNames(userId, Lists.newArrayList(tagSet));
        for (TagDTO tagDTO : tagDTOList) {
            tagSet.remove(tagDTO.getName());
        }
        if (!CollectionUtils.isEmpty(tagSet)) {
            tagService.create(userId,Lists.newArrayList(tagSet));
        }
    }

}

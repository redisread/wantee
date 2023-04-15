package com.jiahongw.wantee.service;

import com.google.common.collect.Sets;
import com.jiahongw.wantee.controller.note.request.CreateNoteDTO;
import com.jiahongw.wantee.entity.NotePO;
import com.jiahongw.wantee.mapper.NotePOMapper;
import com.jiahongw.wantee.model.note.NoteModel;
import com.jiahongw.wantee.model.note.TagModel;
import com.jiahongw.wantee.service.convert.NoteServiceConvert;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author VictorHong
 * @date 2023/4/15
 */
@Service
@Slf4j
public class NoteService {

    @Resource
    private NotePOMapper notePOMapper;

    @Resource
    private TagService tagService;

    public Optional<NoteModel> createNote(CreateNoteDTO createNoteDTO) {
        String content = createNoteDTO.getContent();
        Long creatorId = createNoteDTO.getCreatorId();
        Set<String> tagNameSet = parseTagList(content);
        List<TagModel> tagModelList = tagService.queryTagListByName(creatorId, tagNameSet);
        Set<String> existTagList = tagModelList.stream()
            .map(TagModel::getName)
            .collect(Collectors.toSet());

        Set<String> needCreateTagList = tagNameSet.stream()
            .filter(t -> !existTagList.contains(t))
            .collect(Collectors.toSet());

        List<TagModel> createTagList = tagService.createTag(creatorId, needCreateTagList);

        List<Long> tagIdList = tagModelList.stream()
            .map(TagModel::getId)
            .collect(Collectors.toList());
        for (TagModel tagModel : createTagList) {
            tagIdList.add(tagModel.getId());
        }

        NotePO notePO = NoteServiceConvert.convert2NotePO(createNoteDTO, tagIdList);
        notePOMapper.insert(notePO);
        NoteModel noteModel = NoteServiceConvert.convert2NoteModel(notePO);
        return Optional.of(noteModel);
    }

    private Set<String> parseTagList(String content) {
        // 定义正则表达式
        String regex = "#\\w+";

        // 创建Pattern对象
        Pattern pattern = Pattern.compile(regex);

        // 创建Matcher对象
        Matcher matcher = pattern.matcher(content);

        Set<String> tagNameSet = Sets.newHashSet();

        // 查找所有匹配的标签
        while (matcher.find()) {
            String tag = matcher.group();
            tagNameSet.add(tag);
        }
        return tagNameSet;
    }
}

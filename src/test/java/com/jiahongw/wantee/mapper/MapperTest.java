package com.jiahongw.wantee.mapper;

import com.jiahongw.wantee.BaseTest;
import com.jiahongw.wantee.common.enums.Visibility;
import com.jiahongw.wantee.dto.note.UpdateNoteRequest;
import com.jiahongw.wantee.dto.tag.TagDTO;
import com.jiahongw.wantee.entity.Note;
import com.jiahongw.wantee.service.NoteService;
import com.jiahongw.wantee.service.TagService;
import com.jiahongw.wantee.util.JsonUtils;
import jakarta.annotation.Resource;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

/**
 * @author Redisread
 * @date 2023/5/14
 */
public class MapperTest extends BaseTest {

    @Resource
    private NoteMapperExt noteMapper;

    @Resource
    private TagService tagService;

    @Resource
    private NoteService noteService;

    @Test
    public void qeuryById() {
        Note note = noteMapper.selectOneById(1);
        System.out.println(note);
    }

    @Test
    public void selectTag() {
        Integer userId = 1;
        List<TagDTO>  tagDTOList = tagService.listByUser(userId);
        System.out.println(tagDTOList);
    }

    @Test
    public void getByName() {
        Integer userId = 1;
        String tagName = "A";
        TagDTO tagDTO = tagService.getByName(userId,tagName);
        System.out.println(tagDTO);
    }

    @Test
    public void noteCreate() {
        UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest();
        updateNoteRequest.setContent("#知识 #创造  test");
        updateNoteRequest.setVisibility(Visibility.PUBLIC);
        updateNoteRequest.setResourceIdList(Lists.newArrayList("1","2"));
        updateNoteRequest.setUserId(3);
        noteService.create(updateNoteRequest);
    }

    public static void main(String[] args) {
        List<String> tags = Lists.newArrayList("A","B","C");
        System.out.println(StringUtils.joinWith(",", tags));
        String str =  "[A,B,C]";
        List<String> sList = JsonUtils.parseString2List(str,String.class);
        System.out.println(sList.size() + "  " + sList);
    }
}

package com.jiahongw.wantee.mapper

import com.jiahongw.wantee.BasicSpec
import com.jiahongw.wantee.common.enums.Visibility
import com.jiahongw.wantee.dto.note.UpdateNoteRequest
import com.jiahongw.wantee.entity.Note
import com.jiahongw.wantee.service.NoteService
import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Autowired

class MapperIT extends BasicSpec {

    @Autowired
    private  NoteMapperExt noteMapper

    @Resource
    NoteService noteservice

    def "query by id"() {
        given:
        int id = 1
        when:
        Note note = noteMapper.selectOneById(id)
        then:
        System.out.println(note)
    }

    def "parse"() {
        given:
        int id = 1
        UpdateNoteRequest create = new UpdateNoteRequest()
        create.setContent("#知识 #创造  test")
        create.setVisibility(Visibility.PUBLIC);
        create.setUserId(3);
        when:
        noteservice.create(create)
        then:
        1
    }

}
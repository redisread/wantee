package com.jiahongw.wantee.service

import com.jiahongw.wantee.service.impl.NoteServiceImpl
import spock.lang.Specification


class NoteServiceSpec extends Specification {

    NoteServiceImpl noteService = new NoteServiceImpl()

    def "test parseTagList"() {
        when:
        Set<String> res = noteService.parseTagList(text)
        then:
        res.size() == tagList.size() && res.containsAll(tagList)
        where:
        text                          | tagList
        "人生本来就有很多事情是徒劳提供的。 #认知 #快捷记录" | ["认知", "快捷记录"]
    }
}
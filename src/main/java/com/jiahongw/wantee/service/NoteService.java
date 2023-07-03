package com.jiahongw.wantee.service;

import com.jiahongw.wantee.dto.note.ListNoteRequest;
import com.jiahongw.wantee.dto.note.NoteDTO;
import com.jiahongw.wantee.dto.note.UpdateNoteRequest;
import java.util.List;

public interface NoteService {

    void create(UpdateNoteRequest createRequest);


    void update(UpdateNoteRequest createRequest);

    void archive(Integer noteId);

    void normal(Integer noteId);

    void delete(Integer noteId);

    NoteDTO get(Integer noteId);

    List<NoteDTO> list(ListNoteRequest listNoteRequest);

    List<NoteDTO> all(Integer userId);
}

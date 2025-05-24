package com.edu.todayperfume.note.service;

import com.edu.todayperfume.note.dto.NotesDto;

import java.util.List;

public interface NotesService {
    void saveNote(NotesDto note);
    void updateNote(NotesDto note);
    void deleteNote(Long noteId);
    List<NotesDto> findNotesListAll();
    List<NotesDto> findNotesListByType(Long typeId1, Long typeId2);
}

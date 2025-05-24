package com.edu.todayperfume.perfume.service;

import com.edu.todayperfume.perfume.dto.NotesDto;

import java.util.List;

public interface NotesService {
    void saveNote(NotesDto note);
    void updateNote(NotesDto note);
    void deleteNote(Long noteId);
    List<NotesDto> findNotesListAll();
}

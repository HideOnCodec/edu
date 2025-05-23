package com.edu.todayperfume.perfume.service;

import com.edu.todayperfume.perfume.entity.Notes;

import java.util.List;

public interface NotesService {
    void saveNote(Notes note);
    void updateNote(Notes note);
    void deleteNote(int noteId);
    List<Notes> findAll();
}

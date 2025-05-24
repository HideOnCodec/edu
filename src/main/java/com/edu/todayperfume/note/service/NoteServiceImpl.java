package com.edu.todayperfume.note.service;

import com.edu.todayperfume.note.dto.NotesDto;
import com.edu.todayperfume.note.mapper.NotesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NoteServiceImpl implements NotesService {
    private final NotesMapper notesMapper;

    @Override
    public void saveNote(NotesDto note) {
        log.info("saveNote() :: {}", note.name());
    }

    @Override
    public void updateNote(NotesDto note) {
        log.info("updateNote() :: {}", note.id());
    }

    @Override
    public void deleteNote(Long noteId) {
        log.info("deleteNote() :: {}", noteId);
    }

    @Override
    public List<NotesDto> findNotesListAll() {
        log.info("findNotesListAll()");
        return notesMapper.findNotesListAll();
    }

    @Override
    public List<NotesDto> findNotesListByType(Long typeId1, Long typeId2) {
        log.info("findNotesListByType() :: type1 = {}, type2 = {}", typeId1, typeId2);
        return notesMapper.findNotesListByType(typeId1, typeId2);
    }
}

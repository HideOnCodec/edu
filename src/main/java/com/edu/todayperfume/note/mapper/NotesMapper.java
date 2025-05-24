package com.edu.todayperfume.note.mapper;

import com.edu.todayperfume.note.dto.NotesDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotesMapper {
    List<NotesDto> findNotesListAll();
    List<NotesDto> findNotesListByType(@Param("typeId1") Long typeId1, @Param("typeId2") Long typeId2);
}

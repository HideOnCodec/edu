package com.edu.todayperfume.perfume.mapper;

import com.edu.todayperfume.perfume.dto.NotesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotesMapper {
    List<NotesDto> findNotesListAll();
}

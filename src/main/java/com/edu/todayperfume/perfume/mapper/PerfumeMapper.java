package com.edu.todayperfume.perfume.mapper;

import com.edu.todayperfume.note.dto.NotesDto;
import com.edu.todayperfume.perfume.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PerfumeMapper {
    void save(PerfumeCreateReqDto perfumeCreateReqDto, @Param("updater") String updater);
    void update(PerfumeUpdateReqDto perfumeUpdateReqDto, @Param("updater") String updater);
    void delete(@Param("id") Long id);
    Optional<PerfumeDto> findPerfumeById(@Param("id") Long id);
    List<PerfumeDto> findPerfumeListByNoteOrderByCreatedAt(@Param("noteId") Long noteId);
    List<PerfumeDto> findPerfumeListByNoteOrderByRating(@Param("noteId") Long noteId);
    List<PerfumeDto> recommend(@Param("req") PerfumeRecommendReqDto req, @Param("noteList") List<NotesDto> noteList);
}

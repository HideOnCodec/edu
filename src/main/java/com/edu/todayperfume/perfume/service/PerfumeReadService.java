package com.edu.todayperfume.perfume.service;

import com.edu.todayperfume.note.dto.NotesDto;
import com.edu.todayperfume.perfume.dto.*;

import java.util.List;

public interface PerfumeReadService {
    /** 향수 번호로 한 개의 향수 데이터 조회 */
    PerfumeDto findPerfumeById(Long id);
    /** 최신순 정렬로 모든 향수 가져오기 */
    List<PerfumeDto> findPerfumeListByNoteOrderByCreatedAt(Long noteId);
    /** 별점순 정렬로 모든 향수 가져오기 */
    List<PerfumeDto> findPerfumeListByNoteOrderByRating(Long noteId);
    /** 모든 타입 조회 */
    List<TypeDto> findTypeList();
    /** 향추 추천하기 */
    PerfumeDto recommend(PerfumeRecommendReqDto req, List<NotesDto> noteList);
    List<TypeRankDto> findTypeRankList();
    List<PerfumeRankDto> findPerfumeRankList();
}

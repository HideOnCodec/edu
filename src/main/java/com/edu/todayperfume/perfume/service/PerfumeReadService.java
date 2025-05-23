package com.edu.todayperfume.perfume.service;

import com.edu.todayperfume.perfume.dto.PerfumeRecommendReqDto;
import com.edu.todayperfume.perfume.entity.Perfume;

import java.util.List;
import java.util.Optional;

public interface PerfumeReadService {
    /** 향수 번호로 한 개의 향수 데이터 조회 */
    Optional<Perfume> getPerfumeById(int id);
    /** 최신순 정렬로 모든 향수 가져오기 */
    List<Perfume> getPerfumeListOrderByCreatedAt();
    /** 별점순 정렬로 모든 향수 가져오기 */
    List<Perfume> getPerfumeListOrderByRating();
    /** 타입별 향수 가져오기 */
    List<Perfume> getPerfumeListByNotes(int noteId);
    /** 향추 추천하기 */
    int recommend(PerfumeRecommendReqDto req);
}

package com.edu.todayperfume.perfume.service;

import com.edu.todayperfume.global.exception.BaseCode;
import com.edu.todayperfume.global.exception.CustomException;
import com.edu.todayperfume.perfume.dto.PerfumeDto;
import com.edu.todayperfume.perfume.dto.PerfumeRecommendReqDto;
import com.edu.todayperfume.perfume.dto.TypeDto;
import com.edu.todayperfume.perfume.mapper.PerfumeMapper;
import com.edu.todayperfume.perfume.mapper.TypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PerfumeReadServiceImpl implements PerfumeReadService {
    private final PerfumeMapper perfumeMapper;
    private final TypeMapper typeMapper;

    /**
     * id에 해당하는 perfume 상세 정보 조회
     * @param id
     */
    @Override
    public PerfumeDto findPerfumeById(Long id) {
        log.info("findPerfumeById() :: {}", id);
        return perfumeMapper.findPerfumeById(id)
                .orElseThrow(() -> new CustomException(BaseCode.NOT_EXISTED_EXCEPTION));
    }

    /**
     * perfume 리스트 최신순 정렬 조회
     */
    @Override
    public List<PerfumeDto> findPerfumeListByNoteOrderByCreatedAt(Long noteId) {
        // DB에서 향수 리스트 가져오기
        log.info("findPerfumeListOrderByCreatedAt() :: noteId = {}", noteId);
        return perfumeMapper.findPerfumeListByNoteOrderByCreatedAt(noteId);
    }

    /**
     * perfume 리스트 별점순 정렬 조회
     */
    @Override
    public List<PerfumeDto> findPerfumeListByNoteOrderByRating(Long noteId) {
        // DB에서 향수 리스트 가져오기
        log.info("findPerfumeListOrderByRating() :: noteId = {}", noteId);
        return perfumeMapper.findPerfumeListByNoteOrderByRating(noteId);
    }

    /**
     * 모든 타입 조회
     */
    @Override
    public List<TypeDto> findTypeList() {
        log.info("findTypeList()");
        return typeMapper.findTypeListAll();
    }


    /**
     * 향수 추천 기능
     */
    @Override
    public PerfumeDto recommend(PerfumeRecommendReqDto req) {
        // DB에서 향수 리스트 가져오기
        log.info("recommend() :: type : {} {} {}", req.type1(), req.type2(), req.weather());
        return null;
    }
}

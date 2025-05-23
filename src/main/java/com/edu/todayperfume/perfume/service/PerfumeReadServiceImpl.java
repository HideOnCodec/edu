//package com.edu.todayperfume.perfume.service;
//
//import com.edu.todayperfume.global.exception.BaseCode;
//import com.edu.todayperfume.global.exception.CustomException;
//import com.edu.todayperfume.perfume.dto.PerfumeRecommendReqDto;
//import com.edu.todayperfume.perfume.entity.Perfume;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//public class PerfumeReadServiceImpl implements PerfumeReadService {
//
//    /**
//     * id에 해당하는 perfume 상세 정보 조회
//     * @param id
//     */
//    @Override
//    public Optional<Perfume> getPerfumeById(int id) {
//        Perfume perfume = perfumeRepository.findById(id).orElseThrow(() -> new CustomException(BaseCode.NOT_EXISTED_EXCEPTION));
//        return Optional.ofNullable(perfume);
//    }
//
//    /**
//     * perfume 리스트 최신순 정렬 조회
//     */
//    @Override
//    public List<Perfume> getPerfumeListOrderByCreatedAt() {
//        // DB에서 향수 리스트 가져오기
//        return perfumeRepository.findAll();
//    }
//
//    /**
//     * perfume 리스트 별점순 정렬 조회
//     */
//    @Override
//    public List<Perfume> getPerfumeListOrderByRating() {
//        // DB에서 향수 리스트 가져오기
//        return perfumeRepository.findAllOrderByAvgReview();
//    }
//
//    /**
//     * perfume 리스트 노트별 최신순 조회
//     */
//    @Override
//    public List<Perfume> getPerfumeListByNotes(int noteId) {
//        // DB에서 향수 리스트 가져오기
//        return perfumeRepository.findAllByNotes(noteId);
//    }
//
//    /**
//     * 향수 추천 기능
//     */
//    @Override
//    public int recommend(PerfumeRecommendReqDto req) {
//        // DB에서 향수 리스트 가져오기
//        return perfumeRepository.findRecommendedPerfume(req.type1(), req.type2(), req.weather());
//    }
//}

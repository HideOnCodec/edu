//package com.edu.todayperfume.perfume.service;
//
//import com.edu.todayperfume.global.exception.BaseCode;
//import com.edu.todayperfume.global.exception.CustomException;
//import com.edu.todayperfume.perfume.dto.PerfumeCreateReqDto;
//import com.edu.todayperfume.perfume.dto.PerfumeUpdateReqDto;
//import com.edu.todayperfume.perfume.entity.Notes;
//import com.edu.todayperfume.perfume.entity.Perfume;
//import lombok.RequiredArgsConstructor;
//
//import java.time.LocalDateTime;
//
//
//@RequiredArgsConstructor
//public class PerfumeServiceImpl implements PerfumeService {
//    /**
//     * 향수 정보 생성 후 DB에 저장
//     * @param req
//     */
//    @Override
//    public int create(PerfumeCreateReqDto req) {
//        // 향수 데이터 생성
//        Perfume perfume = Perfume.builder()
//                .name(req.name())
//                .brand(req.brand())
//                .gender(req.gender())
//                .weather(req.weather())
//                .topNote(Notes.builder().id(req.topNote()).build())
//                .middleNote(Notes.builder().id(req.middleNote()).build())
//                .baseNote(Notes.builder().id(req.baseNote()).build())
//                .price(req.price())
//                .createdAt(LocalDateTime.now())
//                .build();
//        // 향수 데이터 DB에 저장
//        return perfumeRepository.save(perfume);
//    }
//
//    /**
//     * 향수 정보 수정 후 DB에 저장
//     * @param id
//     * @param req
//     */
//    @Override
//    public void update(int id, PerfumeUpdateReqDto req) {
//        // DB에서 향수 리스트 가져오기
//        Perfume perfume = perfumeRepository.findById(id).orElseThrow(() -> new CustomException(BaseCode.NOT_EXISTED_EXCEPTION));
//        perfume = perfume.toBuilder()
//                .name(req.name())
//                .brand(req.brand())
//                .gender(req.gender())
//                .weather(req.weather())
//                .topNote(Notes.builder().id(req.topNote()).build())
//                .middleNote(Notes.builder().id(req.middleNote()).build())
//                .baseNote(Notes.builder().id(req.baseNote()).build())
//                .price(req.price())
//                .build();
//        perfumeRepository.update(perfume);
//    }
//
//    /**
//     * id에 해당하는 향수 DB에서 삭제
//     * @param id
//     */
//    @Override
//    public void delete(int id) {
//        // DB에서 향수 리스트 가져오기
//        perfumeRepository.delete(id);
//    }
//}

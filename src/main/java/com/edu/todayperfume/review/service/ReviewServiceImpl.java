//package com.edu.todayperfume.review.service;
//
//import com.edu.todayperfume.global.exception.BaseCode;
//import com.edu.todayperfume.global.exception.CustomException;
//import com.edu.todayperfume.review.dto.ReviewCreateReqDto;
//import com.edu.todayperfume.review.entity.Review;
//import com.edu.todayperfume.user.entity.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ReviewServiceImpl implements ReviewService {
//    /**
//     * 리뷰 생성
//     * @param req
//     */
//    @Override
//    public void create(ReviewCreateReqDto req) {
//        // 새로운 후기 생성
//        Review review = Review.builder()
//                .rate(req.rate())
//                .user(req.user().getId())
//                .perfumeId(req.perfume().getId())
//                .content(req.content())
//                .build();
//
//        reviewRepository.save(review);
//    }
//
//    /**
//     * id에 해당하는 리뷰 삭제
//     * @param id
//     */
//    @Override
//    public void delete(int id) {
//        User loginUser = LoginUtil.getLoginUser();
//        String writer = reviewRepository.findWriterById(id);
//        if(!loginUser.getId().equals(writer)){
//            throw new CustomException(BaseCode.NOT_AUTHORIZED_USER);
//        }
//        reviewRepository.delete(id);
//    }
//
//    /**
//     * 리뷰 최신순 정렬 조회
//     * @return
//     */
//    @Override
//    public List<Review> getAllOrderByCreatedAt(int perfumeId) {
//        return reviewRepository.findAllByPerfumeId(perfumeId);
//    }
//}

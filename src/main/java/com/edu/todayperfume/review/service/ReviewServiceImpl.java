package com.edu.todayperfume.review.service;

import com.edu.todayperfume.global.LoginUtil;
import com.edu.todayperfume.global.exception.BaseCode;
import com.edu.todayperfume.global.exception.CustomException;
import com.edu.todayperfume.global.exception.NotLoginUserException;
import com.edu.todayperfume.review.dto.ReviewCreateReqDto;
import com.edu.todayperfume.review.dto.ReviewDto;
import com.edu.todayperfume.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    /**
     * 리뷰 생성
     * @param req
     */
    @Override
    public void createReview(ReviewCreateReqDto req) {
        log.info("createReview() :: perfumeId = {}", req.perfumeId());
        // 새로운 후기 생성
        String loginUser = LoginUtil.getLoginUser();
        if(loginUser == null) {
            throw new NotLoginUserException();
        }

        // 이미 리뷰를 작성했는지 검사
        Optional<ReviewDto> existed = reviewMapper.findReviewByUserIdAndPerfumeId(loginUser, req.perfumeId());
        if(!existed.isPresent()) {
            reviewMapper.createReview(req, LoginUtil.getLoginUser());
        }
    }

    /**
     * id에 해당하는 리뷰 삭제
     * @param id
     */
    @Override
    public void deleteReview(Long id) {
        log.info("deleteReview() :: {}", id);
        String loginUser = LoginUtil.getLoginUser();
        ReviewDto result = reviewMapper.findReviewById(id).orElse(null);
        // 존재하는 리뷰가 없는 경우
        if(result == null) {
            throw new CustomException(BaseCode.NOT_EXISTED_EXCEPTION);
        }
        if(loginUser == null) {
            throw new NotLoginUserException();
        }
        // 로그인을 안했거나, 로그인한 사용자와 작성자가 다른 경우
        if(!result.writer().equals(loginUser)) {
            throw new CustomException(BaseCode.NOT_AUTHORIZED_USER);
        }

        reviewMapper.deleteReview(id);
    }

    /**
     * 리뷰 최신순 정렬 조회
     * @return
     */
    @Override
    public List<ReviewDto> findReviewListAllOrderByCreatedAt(Long perfumeId) {
        log.info("findReviewListAllOrderByCreatedAt() :: {}", perfumeId);
        return reviewMapper.findReviewListAllOrderByCreatedAt(perfumeId);
    }

    @Override
    public Optional<ReviewDto> findReviewByUserIdAndPerfumeId(String userId, Long perfumeId) {
        log.info("findReviewByUserIdAndPerfumeId() :: user = {}, perfume = {}", userId, perfumeId);
        return reviewMapper.findReviewByUserIdAndPerfumeId(userId, perfumeId);
    }
}

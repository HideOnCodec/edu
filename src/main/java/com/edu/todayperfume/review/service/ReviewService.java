package com.edu.todayperfume.review.service;

import com.edu.todayperfume.review.dto.ReviewCreateReqDto;
import com.edu.todayperfume.review.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    void createReview(ReviewCreateReqDto req);
    void deleteReview(Long id);
    List<ReviewDto> findReviewListAllOrderByCreatedAt(Long perfumeId);
    Optional<ReviewDto> findReviewByUserIdAndPerfumeId(String userId, Long perfumeId);
}

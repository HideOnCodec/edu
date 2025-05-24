package com.edu.todayperfume.review.service;

import com.edu.todayperfume.review.dto.ReviewCreateReqDto;
import com.edu.todayperfume.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    void createReview(ReviewCreateReqDto req);
    void deleteReview(Long id);
    List<ReviewDto> findReviewListAllOrderByCreatedAt(Long perfumeId);
}

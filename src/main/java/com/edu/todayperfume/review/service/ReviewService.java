package com.edu.todayperfume.review.service;

import com.edu.todayperfume.review.dto.ReviewCreateReqDto;
import com.edu.todayperfume.review.entity.Review;

import java.util.List;

public interface ReviewService {
    void create(ReviewCreateReqDto req);
    void delete(int id);
    List<Review> getAllOrderByCreatedAt(int perfumeId);
}

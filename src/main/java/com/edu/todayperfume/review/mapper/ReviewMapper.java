package com.edu.todayperfume.review.mapper;

import com.edu.todayperfume.review.dto.ReviewCreateReqDto;
import com.edu.todayperfume.review.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    Optional<ReviewDto> findReviewById(@Param("id") Long id);
    void createReview(@Param("reqDto") ReviewCreateReqDto reqDto, @Param("writer") String writer);
    void deleteReview(@Param("id") Long id);
    List<ReviewDto> findReviewListAllOrderByCreatedAt(@Param("perfumeId") Long perfumeId);
}

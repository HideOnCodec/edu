package com.edu.todayperfume.review.dto;

public record ReviewCreateReqDto(
        String content,
        Long perfumeId,
        int rate
) {
}

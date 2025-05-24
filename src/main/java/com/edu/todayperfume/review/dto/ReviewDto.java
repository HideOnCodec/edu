package com.edu.todayperfume.review.dto;

public record ReviewDto(
        Long id,
        String content,
        int rate,
        String writer,
        Long perfumeId,
        String createdAtStr
) {
}

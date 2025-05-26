package com.edu.todayperfume.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewCreateReqDto(
        @NotBlank(message = "리뷰 내용은 필수 입력값입니다.")
        @Size(max = 500, message = "리뷰 내용은 500자 이하여야 합니다.")
        String content,
        
        @NotNull(message = "향수 ID는 필수 입력값입니다.")
        Long perfumeId,
        
        int rate
) {
}

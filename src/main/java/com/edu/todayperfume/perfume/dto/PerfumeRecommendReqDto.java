package com.edu.todayperfume.perfume.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PerfumeRecommendReqDto(
    @NotNull(message = "첫 번째 타입은 필수 입력값입니다.")
    Long type1,
    
    @NotNull(message = "두 번째 타입은 필수 입력값입니다.")
    Long type2,
    
    @NotBlank(message = "첫 번째 타입 이름은 필수 입력값입니다.")
    String type1Name,
    
    @NotBlank(message = "두 번째 타입 이름은 필수 입력값입니다.")
    String type2Name,
    
    @NotBlank(message = "날씨는 필수 입력값입니다.")
    String weather
) {
}

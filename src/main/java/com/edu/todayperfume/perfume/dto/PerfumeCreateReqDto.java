package com.edu.todayperfume.perfume.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PerfumeCreateReqDto(
        Long id,
        
        @NotBlank(message = "향수 이름은 필수 입력값입니다.")
        String name,           // 향수 이름
        
        @NotNull(message = "탑 노트는 필수 입력값입니다.")
        Long topNote,         // 탑 노트
        
        @NotNull(message = "미들 노트는 필수 입력값입니다.")
        Long middleNote,      // 미들 노트
        
        @NotNull(message = "베이스 노트는 필수 입력값입니다.")
        Long baseNote,        // 베이스 노트
        
        @NotBlank(message = "날씨는 필수 입력값입니다.")
        String weather,       // 어울리는 날씨
        
        @NotBlank(message = "브랜드 이름은 필수 입력값입니다.")
        String brand,          // 브랜드 이름
        
        @NotNull(message = "가격은 필수 입력값입니다.")
        @Positive(message = "가격은 0보다 커야 합니다.")
        int price,             // 가격
        
        @NotBlank(message = "성별은 필수 입력값입니다.")
        String gender          // 여성 향수 또는 남성 향수
) {}


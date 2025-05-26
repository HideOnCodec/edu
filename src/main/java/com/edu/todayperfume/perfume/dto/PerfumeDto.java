package com.edu.todayperfume.perfume.dto;


public record PerfumeDto(
        Long id,
        String name,         // 향수 이름
        String topNote,       // 탑 노트
        String middleNote,    // 미들 노트
        String baseNote,      // 베이스 노트
        String weather,      // 어울리는 날씨
        String brand,        // 브랜드 이름
        int price,        // 가격
        String priceStr,
        String gender,       // 여성 향수 또는 남성 향수
        double avgReview,     // 평점 평균
        String image
) {}

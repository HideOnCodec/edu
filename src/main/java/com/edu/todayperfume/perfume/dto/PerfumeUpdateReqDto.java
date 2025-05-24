package com.edu.todayperfume.perfume.dto;

public record PerfumeUpdateReqDto(
        Long id,
        String name,           // 향수 이름
        int topNote,        // 탑 노트
        int middleNote,     // 미들 노트
        int baseNote,       // 베이스 노트
        String weather,       // 어울리는 날씨
        String brand,          // 브랜드 이름
        int price,             // 가격
        String gender          // 여성 향수 또는 남성 향수
) {}


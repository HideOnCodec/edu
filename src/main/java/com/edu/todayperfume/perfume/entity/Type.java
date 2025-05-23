package com.edu.todayperfume.perfume.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    FRESH("상큼"),    // 상큼
    FLORAL("꽃향기"),    // 꽃향기
    NATURAL("자연"),   // 자연
    SOFT("부드러움"),      // 부드러움
    SWEET("달콤"),     // 달콤
    HUB("허브"); // 은은

    private String name;

    Type(String name) {
        this.name = name;
    }
}

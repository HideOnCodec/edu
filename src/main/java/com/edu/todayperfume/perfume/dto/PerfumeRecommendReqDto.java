package com.edu.todayperfume.perfume.dto;

import com.edu.todayperfume.perfume.entity.Weather;

import java.lang.reflect.Type;

public record PerfumeRecommendReqDto(
    Type type1,
    Type type2,
    Weather weather
) {
}

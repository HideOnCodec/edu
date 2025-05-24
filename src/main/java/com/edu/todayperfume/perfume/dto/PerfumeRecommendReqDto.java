package com.edu.todayperfume.perfume.dto;

import com.edu.todayperfume.perfume.entity.Weather;

public record PerfumeRecommendReqDto(
    String type1,
    String type2,
    Weather weather
) {
}

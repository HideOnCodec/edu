package com.edu.todayperfume.perfume.dto;

public record PerfumeRankDto(
        String gender,
        String ageGroup,
        Long perfumeId,
        String perfumeName,
        int reviewCnt,
        double avgRate
) {
}

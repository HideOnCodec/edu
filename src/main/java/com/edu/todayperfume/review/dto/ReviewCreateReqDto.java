package com.edu.todayperfume.review.dto;

import com.edu.todayperfume.perfume.entity.Perfume;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public record ReviewCreateReqDto(
        String content,
        Perfume perfume,
        int rate
) {
}

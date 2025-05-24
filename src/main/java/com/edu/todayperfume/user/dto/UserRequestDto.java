package com.edu.todayperfume.user.dto;

public record UserRequestDto(
        String id,
        String password,
        int age,
        String gender
) {
}

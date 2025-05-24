package com.edu.todayperfume.user.dto;

public record UserDto(
        String id,
        String password,
        String gender,
        int age,
        int isDeleted,
        String userType,
        String createdAtStr,
        String updatedAtStr
) {
}

package com.edu.todayperfume.user.dto;

import com.edu.todayperfume.user.entity.UserType;

public record UserDto(
        String id,
        String password,
        boolean isDeleted,
        UserType userType,
        String createdAtStr,
        String updatedAtStr
) {
}

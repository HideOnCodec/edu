package com.edu.todayperfume.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record UserRequestDto(
        @NotBlank(message = "아이디는 필수 입력값입니다.")
        @Size(max = 20, message = "아이디는 20자 이하여야 합니다.")
        String id,
        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        @Size(min = 4, max = 20, message = "비밀번호는 4자 이상 20자 이하여야 합니다.")
        String password,
        int age,
        @NotBlank(message = "성별은 필수 입력값입니다.")
        String gender
) {
}

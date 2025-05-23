package com.edu.todayperfume.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BaseCode {
    // success
    SUCCESS_LOGIN("로그인에 성공하였습니다!"),
    SUCCESS_SIGNUP("가입에 성공하였습니다!"),

    // fail
    FAIL_LOGIN("로그인에 실패하였습니다.."),
    NOT_EXISTED_EXCEPTION("존재하지 않는 데이터입니다."),
    INVALID_INPUT("잘못된 입력입니다."),
    FAIL_LOGOUT("로그인한 유저가 아닙니다."),
    FAIL_USER_DELETE("권한이 없어 회원 탈퇴에 실패하였습니다."),
    NOT_AUTHORIZED_USER("권한이 없습니다."),
    DUPLICATE_USER_ID("중복된 아이디입니다."),
    FAIL_FETCH_WEATHER_INFO("날씨 API 연동 실패");

    private final String message;
}

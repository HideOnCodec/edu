package com.edu.todayperfume.global.exception;

public class CustomException extends RuntimeException {
    public CustomException(BaseCode baseCode) {
        super(baseCode.getMessage());
    }
}

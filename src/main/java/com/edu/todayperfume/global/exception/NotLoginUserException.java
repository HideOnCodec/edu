package com.edu.todayperfume.global.exception;

public class NotLoginUserException extends CustomException{
    public NotLoginUserException() {
        super(BaseCode.NOT_LOGIN_USER);
    }
}

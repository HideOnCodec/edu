package com.edu.todayperfume.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginUserException.class)
    public String notLogin(Model model, NotLoginUserException e) {
        log.info("[ERROR] NotLoginUserException : {}", e.getMessage());
        model.addAttribute("message", "로그인 해주세요.");
        return "user/login";
    }

    @ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException e, Model model) {
        log.info("[ERROR] CustomException : {}", e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "error/other";
    }

    @ExceptionHandler(SQLException.class)
    public String SQLExceptionHandler(SQLException e, Model model){
        log.info("[ERROR] SQLException : {}", e.getMessage());
        e.printStackTrace();
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String SQLExceptionHandler(Exception e, Model model){
        log.info("[ERROR] Exception : {}", e.getMessage());
        e.printStackTrace();
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}

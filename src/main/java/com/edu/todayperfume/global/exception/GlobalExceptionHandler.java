package com.edu.todayperfume.global.exception;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(BadRequestException e) {
        log.error("[ERROR] Bad Request Exception : {}", e);
        e.printStackTrace();
        return "error/400";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, Model model) {
        log.error("[ERROR] RuntimeException : {}", e.getMessage());
        e.printStackTrace();
        return "error/500";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFound(Model model, NotFoundException e) {
        log.error("[ERROR] NotFoundException : {} ",e.getMessage());
        e.printStackTrace();
        return "error/404";
    }

    @ExceptionHandler(NotLoginUserException.class)
    public String notLogin(Model model, NotLoginUserException e) {
        log.error("[ERROR] NotLoginUserException : {}", e.getMessage());
        model.addAttribute("message", "로그인 해주세요.");
        e.printStackTrace();
        return "user/login";
    }

    @ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException e, Model model) {
        log.error("[ERROR] CustomException : {}", e.getMessage());
        model.addAttribute("message", e.getMessage());
        e.printStackTrace();
        return "error/other";
    }

    @ExceptionHandler(SQLException.class)
    public String SQLExceptionHandler(SQLException e, Model model){
        log.error("[ERROR] SQLException : {}", e.getMessage());
        e.printStackTrace();
        model.addAttribute("message", "SQL 문제가 발생하였습니다. 다시 시도해주십시오.");
        return "error/other";
    }

    @ExceptionHandler(Exception.class)
    public String SQLExceptionHandler(Exception e, Model model){
        log.error("[ERROR] Exception : {}", e.getMessage());
        e.printStackTrace();
        model.addAttribute("message", "알 수 없는 문제가 발생하였습니다. 다시 시도해주십시오.");
        return "error/other";
    }
}

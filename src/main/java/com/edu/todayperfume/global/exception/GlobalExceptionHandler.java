package com.edu.todayperfume.global.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public String SQLExceptionHandler(SQLException e, Model model){
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String SQLExceptionHandler(Exception e, Model model){
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}

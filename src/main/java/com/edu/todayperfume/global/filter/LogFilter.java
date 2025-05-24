package com.edu.todayperfume.global.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 메서드, URI, 쿼리
        HttpServletRequest req = (HttpServletRequest) request;
        String method = req.getMethod();
        String uri    = req.getRequestURI();
        String query  = req.getQueryString();
        log.info("[REQUEST] {} {}{}", method, uri, (query != null ? "?"+query : ""));
    }
}

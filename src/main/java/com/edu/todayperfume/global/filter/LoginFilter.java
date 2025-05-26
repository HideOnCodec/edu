package com.edu.todayperfume.global.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Set;

@Slf4j
public class LoginFilter implements Filter {
    private static final Set<String> EXCLUDES = Set.of(
           "/", "/user/login", "/user/signup", "/perfume/list", "/perfume/recommend"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. 캐스팅
        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(req == null || req.getRequestURI() == null){
            return;
        }
        String path = req.getRequestURI().substring(req.getContextPath().length());

        if(EXCLUDES.contains(path) || path.startsWith("/css/") || path.startsWith("/image/")) {
            chain.doFilter(request, response);
            return;
        }

        // 2. 세션 조회 (없으면 null)
        HttpSession session = req.getSession(false);

        // 3. 로그인 체크
        if (session == null || session.getAttribute("loginUser") == null) {
            // 로그인 페이지로 리다이렉트
            log.info("[LoginFilter] 로그인되지 않은 사용자입니다. redirect To LoginPage");
            resp.sendRedirect("/user/login");
            return;
        }
        log.info("[LoginFilter] 로그인 인증 완료");
        // 4. 인증된 요청이면 다음 필터/서블릿 호출
        chain.doFilter(request, response);
    }
}

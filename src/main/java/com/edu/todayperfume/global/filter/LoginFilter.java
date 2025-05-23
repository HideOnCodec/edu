package com.edu.todayperfume.global.filter;


import jakarta.servlet.*;

import java.io.IOException;
import java.util.Set;

public class LoginFilter implements Filter {
    private static final Set<String> EXCLUDES = Set.of(
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. 캐스팅
//        HttpServletRequest req  = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        String path = req.getRequestURI().substring(req.getContextPath().length());
//
//        if(EXCLUDES.contains(path)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // 2. 세션 조회 (없으면 null)
//        HttpSession session = req.getSession(false);
//
//        // 3. 로그인 체크
//        if (session == null || session.getAttribute("id") == null) {
//            // 로그인 페이지로 리다이렉트
//            resp.sendRedirect(req.getContextPath() + "/member/login");
//            return;
//        }

        // 4. 인증된 요청이면 다음 필터/서블릿 호출
        chain.doFilter(request, response);
    }
}

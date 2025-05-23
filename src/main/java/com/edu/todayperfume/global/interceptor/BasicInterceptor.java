package com.edu.todayperfume.global.interceptor;//package com.edu.mybatis.interceptor;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//public class BasicInterceptor implements HandlerInterceptor {
//    private final Logger log = LogManager.getLogger(this.getClass().getSimpleName());
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//
//        log.info(this.getClass().getSimpleName() + " :: postHandle()");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//        log.info(this.getClass().getSimpleName() + " :: afterCompletion()");
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        log.info(this.getClass().getSimpleName() + " :: preHandle()");
//
//        boolean flag = request.getSession().getAttribute("id") != null;
////        flag = false;
//        return true;
////        log.info("preHandle() :: flag={}", flag);
////
////        if (flag) {
////            return true; // 정상. Controller 실행
////        } else {
////            response.sendRedirect(request.getContextPath() + "/member/login");
////            return false; // 비정상. Controller 실행 X
////        }
//
//    }
//
//}

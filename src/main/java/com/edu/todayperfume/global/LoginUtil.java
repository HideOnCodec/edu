package com.edu.todayperfume.global;

import com.edu.todayperfume.user.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class LoginUtil {
    private static final String SESSION_KEY = "loginUser";

    /** 세션에서 loginUser 객체를 꺼내 반환합니다. (없으면 null) */
    @SuppressWarnings("unchecked")
    public static String getLoginUser() {
        HttpSession session = getSession(false);
        if (session == null) return null;
        return (String) session.getAttribute(SESSION_KEY);
    }

    /** 세션에 loginUser 객체를 저장합니다. */
    public static void setLoginUser(String id) {
        HttpSession session = getSession(true);
        session.setAttribute(SESSION_KEY, id);
    }

    /** 세션 무효화(로그아웃) */
    public static void clearLoginUser() {
        HttpSession session = getSession(false);
        if (session != null) {
            session.removeAttribute(SESSION_KEY);
        }
    }

    private static HttpSession getSession(boolean create) {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        if (!(attrs instanceof ServletRequestAttributes)) {
            throw new IllegalStateException("HttpServletRequest를 찾을 수 없습니다.");
        }
        return ((ServletRequestAttributes) attrs).getRequest().getSession(create);
    }
}

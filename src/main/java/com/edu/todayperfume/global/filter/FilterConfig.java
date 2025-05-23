package com.edu.todayperfume.global.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> basicFilter() {

        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();

        // 필터 클래스
        bean.setFilter(new LoginFilter());
        // 필터 우선순위
        bean.setOrder(1);
        // 접근 경로: 필터 클래스 doFilter() 적용 경로. URL 형태로 설정
        bean.addUrlPatterns("/*");	// URL: /filter/basic/
        return bean;
    }
}

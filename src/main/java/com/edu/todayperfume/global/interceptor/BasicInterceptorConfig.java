package com.edu.todayperfume.global.interceptor;//package com.edu.mybatis.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class BasicInterceptorConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//
//		/*
//		# CASE 1
//		*/
//		registry
//		.addInterceptor(new BasicInterceptor())	// 인터셉터 클래스
//		.addPathPatterns("/**")		// 적용 URL
//		.excludePathPatterns("/", "/member/login", "/order/list", "/order/insert");
//	}
//}
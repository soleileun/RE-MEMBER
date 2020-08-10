package com.ssafy.test;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.test.interceptor.JwtInterceptor;

@SpringBootApplication
public class DemoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
		.addPathPatterns("/api/**")
		.excludePathPatterns(Arrays.asList(
				
				"/api/userinfo/**",
				"/api/email/**", "/api/board","/api/board/search/**", "/api/board/typesearch/**",
				 "/api/comments","/api/recruit/**","/api/pinterest/**", 
				 "/api/project/","/api/project/searchByUserId/**","/api/project/**"
				 //, "/api/message/**"
//										"/api/pmember/**","/api/following/**"
//										 "/api/project/**",,
//										 "/api/comments/**","/api/issue/**","/api/interest/**",
//										 "/api/message/**","/api/chat/**","/api/chatroom/**","/api/usertag/**",
//										 "/api/tag/**","/api/addr/**"
				 
//										 
				
				));
	} 	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowedHeaders("*")
		.exposedHeaders("jwt-auth-token");
	}
}

//package com.cwnu.global.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//            // Access-Control-Allow-Origin 설정
//            .allowedOrigins("http://localhost:3000", "http://localhost:8080")
//
//            // 허용할 HTTP 메서드 설정
//            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
//
//            // 허용할 헤더 설정
//            .allowedHeaders("*")
//
//            // 쿠키 허용 여부 설정
//            .allowCredentials(false)
//
//            // preflight 요청 캐시 시간 설정
//            .maxAge(3600);
//    }
//
//}

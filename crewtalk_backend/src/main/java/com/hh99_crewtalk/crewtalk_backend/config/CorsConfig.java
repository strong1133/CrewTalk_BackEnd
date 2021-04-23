package com.hh99_crewtalk.crewtalk_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean // DI 해주기 위한 Bean 등록
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //내 서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정
        config.addAllowedOriginPattern("*");
        config.addAllowedOrigin("http://**"); // 모든 IP의 응답을 허용
        config.addAllowedHeader("*"); // 모든 header의 응답을 허용
        config.addAllowedMethod("*"); // 모든 Request {Post, Get, Delete, Put}형태의 요청을 허용
        source.registerCorsConfiguration("/api/**", config); // api의 형태로 요청이 들어오면 모두 config에 등록된 명세를 거침
        return new CorsFilter(source); // 위의 과정들로 만든 source 설정을 CorsFilter 객체에 반환
    }

}

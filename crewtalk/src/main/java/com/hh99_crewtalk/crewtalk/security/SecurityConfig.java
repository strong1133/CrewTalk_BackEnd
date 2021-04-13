package com.hh99_crewtalk.crewtalk.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web
                // Spring Security가 무시할 요청의 주소들을 추가합니다.
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers(
                        "/h2-console/**"
                        , "/favicon.ico"
                );
    }
}

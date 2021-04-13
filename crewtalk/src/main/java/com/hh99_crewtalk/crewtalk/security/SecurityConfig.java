package com.hh99_crewtalk.crewtalk.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Spring Security에서는 클라이언트로부터 post 요청을 받았을 때 기본적으로 http 메세지에 요청 토큰(csrf-token)이 포함된 채로 건네져 와야 하도록 설정되어 있습니다.
                // (이 요청 토큰은 일반적으로 페이지를 내려줄 때 form에 숨겨진 input태그의 값으로 렌더링하여 내려주게 됩니다.)
                // 하지만 개발시에는 편의상 이를 비활성화합니다.
                // https://reiphiel.tistory.com/entry/spring-security-csrf
                .csrf().disable()

                // 특정 주소의 하위 주소들의 요청에 대해서만 인증 여부 상관없이 응답하고,
                // 그 외 주소들은 인증이 필요하도록 설정합니다.
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated();
    }
}

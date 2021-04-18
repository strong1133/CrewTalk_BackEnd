package com.hh99_crewtalk.crewtalk_backend.config;


import com.hh99_crewtalk.crewtalk_backend.config.jwt.JwtAuthenticationFilter;
import com.hh99_crewtalk.crewtalk_backend.config.jwt.JwtAuthorizationFilter;
import com.hh99_crewtalk.crewtalk_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration // DI를 위한 IOC등록
@EnableWebSecurity // 시큐리티 적용 시작 -> 스프링 필터 체인에 등록된다.
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsConfig corsConfig; // CorsConfig에서 만든 Filter를 사용하기 위한 DI
    private final UserRepository userRepository;
//    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin(); // H2 콘솔 사용시 X-Frame 차단 방지
        http

                .addFilter(corsConfig.corsFilter()) // Cors필터링 -> 모든 요청은 이 필터를 거치게 되며 필터에서 모두 허용해줌으로 cors에러 방지
                // CrossOrigin 과의 차이점 :: CrossOrigin은 인증이 필요한 요청은 커버를 할 수 없음.

                .csrf().disable() // 토큰 만으로 요청을 보낼 수 있으면 보안상 취약함으로 헤더를 통해 요청해야만 하게끔 함. -> 보안상 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //JWT토큰 사용을 위해서 세션 비활성화
            .and()
                .formLogin().disable() //우리는 시큐리티 디폴트 로그인화면을 사용하지 않음.

                // 하지만 로그인 시도를 캐치해줄 수 있는 다른 녀석을 만들어 줘야함.
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                // AuthenticationManager 이녀석은 로그인 매니져라고 생각하면 되며, JwtAuthenticationFilter 이녀석이 로그인 수행 필터이기 때문에
                // 매니저와 함께 해야 하므로 JwtAuthenticationFilter를 던져주면 된다.
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                // JwtAuthorizationFilter 로그인 할 당시 클라이언트에게 준 토큰을 이용해 이후 요청이 들어오면 토큰이 유효한
                // 토큰인지를 검사해서 토큰에 해당하는 유저정보를 검색해준다. 유저 정보에 접근하기 위해 userRepository를 실어준다
                .authorizeRequests()
                .antMatchers("/api/**").permitAll() //api 사용을 허용
                .antMatchers("/h2-console/**").permitAll() //h2 콘솔 사용을 허용
                .antMatchers("/**").permitAll() //h2 콘솔 사용을 허용

                .anyRequest().authenticated();// antMatchers 로 허용한 요청 외에는 모두 인증 요구


    }



}

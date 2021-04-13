package com.hh99_crewtalk.crewtalk.controller;

import com.hh99_crewtalk.crewtalk.dto.JwtDto;
import com.hh99_crewtalk.crewtalk.dto.AuthRequestDto;
import com.hh99_crewtalk.crewtalk.security.jwt.JwtFilter;
import com.hh99_crewtalk.crewtalk.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final JwtProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/api/auth")
    public ResponseEntity<JwtDto> authenticate(@RequestBody AuthRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(requestDto.getUserId(), requestDto.getPassword());

        // AuthenticationManager.authenticate 메소드를 호출할 때 그 내부에서
        // CustomUserService의 loadUserByUsername 메소드가 호출됩니다.
        // loadUserByUsername으로 리턴받은 UserDetails가 현재 requestDto에 담긴 정보와 일치하는지 판별하고,
        // 일치한다면 authenticate함수가 올바른 Authenticate객체가 리턴됩니다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);

        // 가져온 authentication으로 createToken하여 JWT Token을 생성해냅니다.
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, JwtFilter.TYPE_PREFIX + jwt);

        return new ResponseEntity<>(new JwtDto(jwt), httpHeaders, HttpStatus.OK);
    }
}

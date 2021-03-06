package com.hh99_crewtalk.crewtalk_backend.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh99_crewtalk.crewtalk_backend.config.Exception.GlobalExceptionHandler;
import com.hh99_crewtalk.crewtalk_backend.config.auth.PrincipalDetails;
import com.hh99_crewtalk.crewtalk_backend.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


//스프링 시큐리에서 UsernamePasswordAuthenticationFilter라는 것을 제공해줌
//loginForm.disable 했기 때문에 /login 요청시 이걸 시큐리티 로그인으로 인터셉트 해줘야함.
// UsernamePasswordAuthenticationFilter라는 필터를 걸어서 username, password를 /login으로 Post 요청하면 해당 필터에 걸리게끔 설정.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager; // 로그인의 동반자 AuthenticationManager

    // Authentication 객체를 만들어서 리턴 => 의존: AuthenticationManager
    // /login 시 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        System.out.println("JwtAuthenticationFilter : 진입");

        //로그인 request에 실어서 온 username과 password를 JSON형태로 꺼내줌
        ObjectMapper om = new ObjectMapper();
        LoginRequestDto loginRequestDto = null;
        try {
            loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class); // 받아온 값들을 dto에 넣어주고
            System.out.println("loginRequestDto :" + loginRequestDto);
        } catch (Exception e) {
            System.out.println("로그인 실패");
        }
        // JwtAuthenticationFilter에 위에서 담아준 loginRequestDto가 잘 물렸는지 확인
        System.out.println("JwtAuthenticationFilter : " + loginRequestDto);

        //loginRequestDto 의 값들로 임시 토큰을 만듬 -> 인증 안된 토큰
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()); // dto에서 다시 값을 받아와 토큰을 만드는 재료로 사용

        System.out.println("JwtAuthenticationFilter : 토큰생성완료");
        System.out.println("UsernamePasswordAuthenticationToken : " + authenticationToken);

        // authenticate() 함수가 호출 되면 인증 프로바이더가 유저 디테일 서비스의
        // loadUserByUsername(토큰의 첫번째 파라메터) 를 호출하고
        // UserDetails를 리턴받아서 토큰의 두번째 파라메터(credential)과
        // UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
        // Authentication 객체를 만들어서 필터체인으로 리턴해준다.

        // Tip: 인증 프로바이더의 디폴트 서비스는 UserDetailsService 타입
        // Tip: 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
        // 결론은 인증 프로바이더에게 알려줄 필요가 없음.

        //authentication에 위에서 임시로 만든 토큰을 물려줌
        Authentication authentication = null;
        authentication = authenticationManager.authenticate(authenticationToken);

        // loadUserByUsername에 authentication를 던져줌 -> UserDetails까지 들어가서 credential와 UserDetails(DB값)의 getPassword()함수로 비교.
        // 동일하면 Authentication 객체를 만들어서 리턴
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("Authentication : " + principalDetails.getUser().getUsername());
        System.out.println("UsernamePasswordAuthenticationToken : " + authentication);
        return authentication;
    }

    //JWT토큰을 생성해서 responsed 에 담아
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // DB매칭에 성공한 Authentication를 authResult에 담아줌.
        // authResult.getPrincipal() 를 통해 authResult에서 인증 정보를 뽑아옴.
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // authResult에서 가져온 인증정보를 이용해 JWT토큰을 만들어 줄 것임.
        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        System.out.println(jwtToken);

        // 만들어진 토큰을 응답 헤더에 실어준다.
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);

    }
}

package com.hh99_crewtalk.crewtalk.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilterBean {
    private final JwtProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String jwt = getTokenFromRequest(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        // jwt가 유효한지 확인하고, 유효하다면 SecurityContext에 현재 인증 정보를 캐싱합니다.
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.parseToken(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String AUTHORIZATION_HEADER = "Authorization";
        final String TYPE_PREFIX = "Bearer ";

        // JwtToken은 http 요청 헤더의 Authorization필드에 담겨져 옵니다.
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        // 실제 JwtToken 문자열은 "Bearer " 이후에 시작됩니다.
        // "Bearer "은 이 Token값이 어떤 타입인지에 대해 설명하는 값입니다.
        // https://velog.io/@cada/%ED%86%A0%EA%B7%BC-%EA%B8%B0%EB%B0%98-%EC%9D%B8%EC%A6%9D%EC%97%90%EC%84%9C-bearer%EB%8A%94-%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TYPE_PREFIX)) {
            return bearerToken.substring(TYPE_PREFIX.length());
        }

        return null;
    }

}

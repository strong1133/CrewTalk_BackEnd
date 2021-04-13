package com.hh99_crewtalk.crewtalk.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
public class SecurityUtil {

    /**
     * 이번 요청을 발생시킨 사용자의 userId를 반환합니다.
     * 만약 사용자가 인증되어 있지 않은 상태라면 Optional.empty()를 반환합니다.
     * <p>
     * JwtFilter에 의해 유효한 Jwt를 전달받았을 때 SecurityContext에 Authentication 정보가 저장되는데,
     * 이 메소드에서는 그 SecurityContext를 통해 Authentication에 접근하게 됩니다.
     *
     * @return 이번 요청을 발생시킨 사용자의 정보를 반환합니다.
     */
    public static Optional<String> getCurrentRequestUserId() {
        // JwtFilter.doFilter에서 저장한 SecurityContext의 인증 정보를 가져옵니다.
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String userId = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            userId = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            userId = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(userId);
    }
}

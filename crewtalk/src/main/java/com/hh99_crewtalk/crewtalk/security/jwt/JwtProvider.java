package com.hh99_crewtalk.crewtalk.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    private final String secret;
    private final long tokenValidityInMilliseconds;

    private final Key key;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 전달한 인증 정보에 대응되는 Jwt를 반환합니다.
     * 이 메소드는 주로 로그인을 시도했을 때, 입력한 username과 password가 유효한 경우 Jwt를 반환해주기 위해 호출됩니다.
     *
     * @param authentication 인증 정보입니다.
     * @return 인증 정보에 대응되는 Jwt입니다.
     */
    public String createToken(Authentication authentication) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    /**
     * 전달한 Jwt에 대응되는 인증 정보를 반환합니다.
     * 이 메소드는 주로 사용자 인증 정보가 필요한 요청을 받았을 때, 요청한 사용자 username정보를 토큰에서 파싱하기 위해 호출됩니다.
     * 이 때 반환되는 Authentication의 credentials값은 무조건 비어 있습니다. Jwt에는 username밖에 담기지 않기 때문입니다.
     *
     * @param token 사용자가 보내온 Jwt입니다.
     * @return Jwt에 대응되는 인증 정보입니다.
     */
    public Authentication parseToken(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        ArrayList<GrantedAuthority> emptyAuthorities = new ArrayList<>(0);

        // token으로부터 user정보를 가져올 때에는 비밀번호를 사용하지 않습니다.
        // 오로지 token에 있는 username만으로 user를 가져옵니다.
        String username = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(username, null, emptyAuthorities);
    }

    /**
     * Jwt의 유효성을 검사합니다.
     * 이 메소드는 주로 사용자 인증 정보가 필요한 요청을 받았을 때, Jwt에서 요청한 사용자 username을 파싱하기 전에 앞서 이 Jwt가 유효한지부터 검증하기 위해 호출됩니다.
     * Jwt가 유효하지 않는 케이스는 다음과 같습니다.
     * 1. Jwt서명이 잘못된 경우
     * 2. Jwt의 유효 기간이 만료된 경우
     * 3. 지원하지 않는 Jwt 형식인 경우
     *
     * @param token 사용자가 보내온 Jwt입니다.
     * @return Jwt가 유효한지에 대한 여부입니다.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

}

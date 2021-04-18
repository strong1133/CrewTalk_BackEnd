package com.hh99_crewtalk.crewtalk_backend.config.auth;

import com.hh99_crewtalk.crewtalk_backend.config.Exception.GlobalExceptionHandler;
import com.hh99_crewtalk.crewtalk_backend.domain.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.awt.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails  {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword () throws IllegalArgumentException {
        System.out.println("pw :" + "진입");
        String pw = null;
        try {
            pw = user.getPassword();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("로그인 정보가 잘못 되었습니다.");

        }

        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        return authorities;
    }

}

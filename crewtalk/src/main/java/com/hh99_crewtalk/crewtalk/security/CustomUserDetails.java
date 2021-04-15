package com.hh99_crewtalk.crewtalk.security;

import com.hh99_crewtalk.crewtalk.domain.Member;

import java.util.ArrayList;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    public CustomUserDetails(Member member) {
        super(member.getUsername(), member.getPassword(), new ArrayList<>(0));
    }
}

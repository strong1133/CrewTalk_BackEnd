package com.hh99_crewtalk.crewtalk.security;

import com.hh99_crewtalk.crewtalk.domain.Member;
import com.hh99_crewtalk.crewtalk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "-> 해당하는 username을 가진 Member가 없습니다."));

        return new CustomUserDetails(member);
    }

}

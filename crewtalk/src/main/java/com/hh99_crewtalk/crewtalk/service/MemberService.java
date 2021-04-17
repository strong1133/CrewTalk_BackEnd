package com.hh99_crewtalk.crewtalk.service;


import com.hh99_crewtalk.crewtalk.domain.Member;
import com.hh99_crewtalk.crewtalk.dto.MemberResponseDto;
import com.hh99_crewtalk.crewtalk.dto.MemberUpdateDto;
import com.hh99_crewtalk.crewtalk.dto.SignupRequestDto;
import com.hh99_crewtalk.crewtalk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //전체 유저 조회
    @Transactional
    public List<MemberResponseDto> findAllUser() {
        List<Member> memberList = memberRepository.findAll();

        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>(memberList.size());
        memberList.forEach(member -> {
            memberResponseDtoList.add(new MemberResponseDto(member));
        });

        return memberResponseDtoList;
    }

    // 스택별 유저 조회
    @Transactional
    public List<MemberResponseDto> findAllUserByStack(String stack) {
        List<Member> memberList = memberRepository.findAllByStack(stack);

        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>(memberList.size());
        memberList.forEach(member -> {
            memberResponseDtoList.add(new MemberResponseDto(member));
        });

        return memberResponseDtoList;
    }

    //회원가입
    @Transactional
    public MemberResponseDto createUser(SignupRequestDto signupRequestDto) {
        String encryptedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        Member member = new Member(signupRequestDto);
        member.setPassword(encryptedPassword);
        memberRepository.save(member);

        MemberResponseDto responseDto = new MemberResponseDto(member);
        return responseDto;
    }

    //유저 정보 변경
    @Transactional
    public Long updateMember(Long id, MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        member.updateMember(memberUpdateDto);
        return member.getId();
    }


}

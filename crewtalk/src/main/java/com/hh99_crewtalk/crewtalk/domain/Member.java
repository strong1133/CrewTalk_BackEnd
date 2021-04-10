package com.hh99_crewtalk.crewtalk.domain;

import com.hh99_crewtalk.crewtalk.dto.MemberUpdateDto;
import com.hh99_crewtalk.crewtalk.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String stack;

    public Member(SignupRequestDto signupRequestDto){
        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();
        this.nickname = signupRequestDto.getNickname();
        this.stack = signupRequestDto.getStack();
    }

    // 유저정보 수정
    public void updateMember(MemberUpdateDto memberUpdateDto){
        this.nickname = memberUpdateDto.getNickname();
        this.stack = memberUpdateDto.getStack();
    }

}

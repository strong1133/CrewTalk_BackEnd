package com.hh99_crewtalk.crewtalk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hh99_crewtalk.crewtalk.dto.MemberUpdateDto;
import com.hh99_crewtalk.crewtalk.dto.SignupRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String stack;

    public Member(SignupRequestDto signupRequestDto) {
        this.userId = signupRequestDto.getUserId();
        this.password = signupRequestDto.getPassword();
        this.stack = signupRequestDto.getStack();
    }

    // 유저정보 수정
    public void updateMember(MemberUpdateDto memberUpdateDto) {
        this.userId = memberUpdateDto.getUserId();
        this.stack = memberUpdateDto.getStack();
    }

}

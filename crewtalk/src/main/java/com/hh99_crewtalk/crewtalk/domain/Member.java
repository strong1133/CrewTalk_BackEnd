package com.hh99_crewtalk.crewtalk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hh99_crewtalk.crewtalk.dto.MemberUpdateDto;
import com.hh99_crewtalk.crewtalk.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String stack;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    @OrderBy(value = "createdAt DESC")
    private List<Article> articleList;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    @OrderBy(value = "createdAt DESC")
    private List<Comment> commentList;

    public Member(SignupRequestDto signupRequestDto) {
        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();
        this.stack = signupRequestDto.getStack();
    }

    // 유저정보 수정
    public void updateMember(MemberUpdateDto memberUpdateDto) {
        this.username = memberUpdateDto.getUsername();
        this.stack = memberUpdateDto.getStack();
    }

}

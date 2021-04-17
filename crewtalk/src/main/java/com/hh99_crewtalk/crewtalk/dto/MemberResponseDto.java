package com.hh99_crewtalk.crewtalk.dto;

import com.hh99_crewtalk.crewtalk.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;

    private String username;

    private String stack;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.stack = member.getStack();
    }
}

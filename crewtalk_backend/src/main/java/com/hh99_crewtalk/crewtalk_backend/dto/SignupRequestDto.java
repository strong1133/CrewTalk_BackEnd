package com.hh99_crewtalk.crewtalk_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//회원가입을 위한 DTO
public class SignupRequestDto {

    private String username;  // 회원 ID

    private String password; // 비밀번호

    private String name;  // 회원 실제 이름

    private String stack; // 회원 주특기
}

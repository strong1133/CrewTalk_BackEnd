package com.hh99_crewtalk.crewtalk.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class SignupRequestDto {

    private String username;

    private String password;

    private String nickname;

    private String stack;
}

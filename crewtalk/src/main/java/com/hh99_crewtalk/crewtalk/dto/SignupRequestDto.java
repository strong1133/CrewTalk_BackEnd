package com.hh99_crewtalk.crewtalk.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class SignupRequestDto {

    private String userId;

    private String password;

    private String stack;
}

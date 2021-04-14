package com.hh99_crewtalk.crewtalk_backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hh99_crewtalk.crewtalk_backend.dto.SignupRequestDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
//@Data
@Entity
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String stack;

    public User (SignupRequestDto signupRequestDto){
        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();
        this.name = signupRequestDto.getName();
        this.stack = signupRequestDto.getStack();
    }
}
package com.hh99_crewtalk.crewtalk.controller;

import com.hh99_crewtalk.crewtalk.dto.MemberResponseDto;
import com.hh99_crewtalk.crewtalk.dto.SignupRequestDto;
import com.hh99_crewtalk.crewtalk.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;


    //회원 전체 조회
    @ResponseBody
    @GetMapping("/api/user/all")
    public List<MemberResponseDto> findAllUser() {
        return memberService.findAllUser();
    }

    //스택별 회원 조회
    @ResponseBody
    @GetMapping("/api/user/{stack}")
    public List<MemberResponseDto> findAllUserByStack(@PathVariable String stack) {
        return memberService.findAllUserByStack(stack);
    }

    //회원가입 (단순)
    @ResponseBody
    @PostMapping("/api/signup")
    public MemberResponseDto createUser(@RequestBody SignupRequestDto signupRequestDto) {
        return memberService.createUser(signupRequestDto);
    }

}

package com.hh99_crewtalk.crewtalk.controller;

import com.hh99_crewtalk.crewtalk.dto.MemberResponseDto;
import com.hh99_crewtalk.crewtalk.dto.MessageResponseDto;
import com.hh99_crewtalk.crewtalk.dto.SignupRequestDto;
import com.hh99_crewtalk.crewtalk.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/api/user/{username}")
    public ResponseEntity<String> getUserByUsername(@PathVariable String username) {
        try {
            MemberResponseDto memberResponseDto = memberService.getUserByUsername(username);

            return new ResponseEntity<>(new JSONObject(memberResponseDto).toString(), HttpStatus.OK);
        } catch (Exception e) {
            MessageResponseDto messageResponseDto = new MessageResponseDto(e.getMessage());

            return new ResponseEntity<>(new JSONObject(messageResponseDto).toString(), HttpStatus.BAD_REQUEST);
        }
    }

    //회원 전체 조회
    @ResponseBody
    @GetMapping("/api/user/all")
    public List<MemberResponseDto> findAllUser() {
        return memberService.findAllUser();
    }

    //스택별 회원 조회
    @ResponseBody
    @GetMapping("/api/user")
    public List<MemberResponseDto> findAllUserByStack(@RequestParam String stack) {
        return memberService.findAllUserByStack(stack);
    }

    //회원가입 (단순)
    @ResponseBody
    @PostMapping("/api/signup")
    public MemberResponseDto createUser(@RequestBody SignupRequestDto signupRequestDto) {
        return memberService.createUser(signupRequestDto);
    }

}

package com.hh99_crewtalk.crewtalk_backend.controller;

import com.hh99_crewtalk.crewtalk_backend.domain.User;
import com.hh99_crewtalk.crewtalk_backend.dto.SignupRequestDto;
import com.hh99_crewtalk.crewtalk_backend.repository.SignupRepository;
import com.hh99_crewtalk.crewtalk_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;
    private final SignupRepository signupRepository;

    // 회원 가입
    @PostMapping("/api/user/signup")
    public Map<String, String> registerUser(@RequestBody SignupRequestDto signupRequestDto) {
        User user = userService.registerUser(signupRequestDto);
        Map<String, String> map = new HashMap<>(); //JSON형태의 성공 메세지
        map.put("Success","회원 가입이 완료 되었습니다.");
        System.out.println(user);
        signupRepository.save(user);
        return map;
    }

}

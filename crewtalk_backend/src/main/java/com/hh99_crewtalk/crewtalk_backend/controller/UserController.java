package com.hh99_crewtalk.crewtalk_backend.controller;

import com.hh99_crewtalk.crewtalk_backend.domain.User;
import com.hh99_crewtalk.crewtalk_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/api/user/cur_user")
    public Optional<User> findCurUser(Authentication authentication){
        return userService.findCurUser(authentication);
    }

    //회원 전체 조회
    @GetMapping("/api/user/all")
    public List<User> findAllUser() {
        return userService.findAllUser();
    }

    //스택별 회원 조회
    @GetMapping("/api/user")
    public List<User> findAllUserByStack(@RequestParam String stack) {
        return userService.findAllUserByStack(stack);
    }
}

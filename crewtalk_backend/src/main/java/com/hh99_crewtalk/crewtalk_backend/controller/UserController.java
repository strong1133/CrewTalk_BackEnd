package com.hh99_crewtalk.crewtalk_backend.controller;

import com.hh99_crewtalk.crewtalk_backend.domain.User;
import com.hh99_crewtalk.crewtalk_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    //현재 로그인한 유저 정보 + 인가
    @GetMapping("/api/user/cur_user")
    public Optional<User> findCurUser(Authentication authentication){
        return userService.findCurUser(authentication);
    }

    //회원 전체 조회  + 페이징
    @GetMapping("/api/user/all")
    public List<User> findAllUser(@RequestParam int page) {
        return userService.findAllUser(page);
    }

    //스택별 회원 조회 + 페이징
    @GetMapping("/api/user")
    public List<User> findAllUserByStack(@RequestParam String stack, int page) {
        return userService.findAllUserByStack(stack, page);
    }

    // 검색어가 포함된 이름을 갖는 회원 조회 + 페이징
    @GetMapping("/api/user/search")
    public List<User> findByNameContaining(@RequestParam String name, int page){
        return userService.findByNameContaining(name, page);
    }

    // 특정 유저 정보 단일 조회
    @GetMapping("/api/user/detail")
    public Optional<User> findSelectedUser(@RequestParam Long id){
        return userService.findSelectedUser(id);
    }

}

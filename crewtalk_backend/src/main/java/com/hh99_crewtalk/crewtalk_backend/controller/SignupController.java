package com.hh99_crewtalk.crewtalk_backend.controller;

import com.hh99_crewtalk.crewtalk_backend.config.auth.PrincipalDetails;
import com.hh99_crewtalk.crewtalk_backend.domain.User;
import com.hh99_crewtalk.crewtalk_backend.dto.SignupRequestDto;
import com.hh99_crewtalk.crewtalk_backend.repository.SignupRepository;
import com.hh99_crewtalk.crewtalk_backend.repository.UserRepository;
import com.hh99_crewtalk.crewtalk_backend.service.UserService;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final SignupRepository signupRepository;

    @PostMapping("/api/user/signup")
    public User registerUser(@RequestBody SignupRequestDto signupRequestDto) {
        User user = userService.registerUser(signupRequestDto);
        System.out.println(user);
        signupRepository.save(user);
        return user;
    }

    @GetMapping("/api/vi/user")
    public User finduser(Authentication authentication ) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long id = principalDetails.getUser().getId();

        User user = userRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("No")
        );
        return user;
    }

    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}

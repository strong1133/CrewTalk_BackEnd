package com.hh99_crewtalk.crewtalk_backend.service;

import com.hh99_crewtalk.crewtalk_backend.domain.User;
import com.hh99_crewtalk.crewtalk_backend.dto.SignupRequestDto;
import com.hh99_crewtalk.crewtalk_backend.repository.SignupRepository;
import com.hh99_crewtalk.crewtalk_backend.repository.UserRepository;
import com.hh99_crewtalk.crewtalk_backend.util.SignupValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;
    private final SignupRepository signupRepository;
    private static final String SCERET_KEY="AWDSDV+/asdwzwr3434@#$vvadflf00ood/[das";

    //회원가입
    public User registerUser(SignupRequestDto signupRequestDto){
        //ID
        String username =signupRequestDto.getUsername();
        System.out.println("username : "+ username);
        if (!SignupValidator.idValid(username)){
            throw new IllegalArgumentException("아이디 형식이 올바르지 않습니다!");
        }
        //중복검사
        Optional<User> foundUsername = signupRepository.findByUsername(username);
        if (foundUsername.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다!");
        }

        //PW
        String lawPassword =signupRequestDto.getPassword();
        //정규식 검사
        if(!SignupValidator.pwValid(username, lawPassword)){
            throw new IllegalArgumentException("비밀번호 형식이 올바르지 않습니다!");
        }
        //정규식 통과한 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        signupRequestDto.setUsername(username);
        signupRequestDto.setPassword(encodedPassword);


        //위의 모든 조건 통과 시 암호화된 사용자 계정 정보를 DB에 저장
        User user = new User(signupRequestDto);
        System.out.println("user : "+ user);
        return user;
    }
}
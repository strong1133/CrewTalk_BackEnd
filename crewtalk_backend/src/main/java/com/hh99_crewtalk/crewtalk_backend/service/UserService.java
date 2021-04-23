package com.hh99_crewtalk.crewtalk_backend.service;

import com.hh99_crewtalk.crewtalk_backend.config.auth.PrincipalDetails;
import com.hh99_crewtalk.crewtalk_backend.domain.User;
import com.hh99_crewtalk.crewtalk_backend.dto.SignupRequestDto;
import com.hh99_crewtalk.crewtalk_backend.repository.SignupRepository;
import com.hh99_crewtalk.crewtalk_backend.repository.UserRepository;
import com.hh99_crewtalk.crewtalk_backend.util.SignupValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
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
        String encodedPassword = passwordEncoder.encode(lawPassword + SCERET_KEY);

        signupRequestDto.setUsername(username);
        signupRequestDto.setPassword(encodedPassword);


        //위의 모든 조건 통과 시 암호화된 사용자 계정 정보를 DB에 저장
        User user = new User(signupRequestDto);
        System.out.println("user : "+ user);
        return user;

    }

    //전체 유저 조회
    @Transactional
    public List<User> findAllUser(int page) {
        Page<User> pageUsers = userRepository.findAll(PageRequest.of(page-1, 6, Sort.Direction.DESC, "modifiedAt"));
        List<User> users = pageUsers.getContent();
        return users;
    }

    // 스택별 유저 조회
    @Transactional
    public List<User> findAllUserByStack(String stack, int page) {
        Page<User> pageUserByStack = userRepository.findAllByStack(stack, PageRequest.of(page-1, 6, Sort.Direction.DESC, "modifiedAt"));
        List<User> userByStack = pageUserByStack.getContent();
        return userByStack;
    }

    @Transactional
    public List<User> findByNameContaining(String name, int page){
        Page<User> pageUserByNameContain = userRepository.findByNameContaining(name, PageRequest.of(page-1, 6, Sort.Direction.DESC, "modifiedAt"));
        List<User> userByNameContain = pageUserByNameContain.getContent();
        return userByNameContain;
    }

    // 현재 로그인한 유저 정보
    @Transactional
    public Optional<User> findCurUser(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        // 그 중에서도 primary Key인 id값
        Long user_id = principalDetails.getUser().getId();
        return userRepository.findById(user_id);
    }

    // 특정 유저 정보 단일 조회
    @Transactional
    public Optional<User> findSelectedUser(Long id){
        return userRepository.findById(id);
    }

}

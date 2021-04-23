package com.hh99_crewtalk.crewtalk_backend.util;


import java.util.regex.Pattern;

// 정규식 검사 클래스
public class SignupValidator {

    //Id
    public static boolean idValid(String username){
        return Pattern.matches( "^[A-za-z0-9]{4,15}", username);
    }

    //PW
    public static boolean pwValid(String id, String pw) {
        return Pattern.matches("^[A-Za-z0-9]{4,20}$", pw);
    }
}

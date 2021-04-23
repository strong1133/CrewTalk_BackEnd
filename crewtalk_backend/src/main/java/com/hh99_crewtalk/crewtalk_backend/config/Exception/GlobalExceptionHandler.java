package com.hh99_crewtalk.crewtalk_backend.config.Exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Controller 요청에 대해서 에러가 발생한 응답을 캐치해줌
@RestController
public class GlobalExceptionHandler  {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // GlobalExceptionHandler에서 낚아챈 에러를 모두 400 에러로 만듬.
    public Map<String, String> handleException (Exception e){
        Map<String, String> map = new HashMap<>(); // 에러메시지를 JSON 형태로 반환
        map.put("err", e.getMessage()); // 에러 발생시 throws 한 에러 메세지를 받아옴.
        return map;
    }

}

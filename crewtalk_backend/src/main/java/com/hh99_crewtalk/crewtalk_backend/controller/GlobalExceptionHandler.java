package com.hh99_crewtalk.crewtalk_backend.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Map<String, String> handleException (Exception e){
        Map<String, String> map = new HashMap<>();
        map.put("err", e.getMessage());
        return map;
    }
    @ExceptionHandler(value = NullPointerException.class)
    public Map<String, String> handleException (NullPointerException e){
        Map<String, String> map = new HashMap<>();
        map.put("err", e.getMessage());
        return map;
    }
    @ExceptionHandler(value = AuthenticationException.class)
    public Map<String, String> handleException (AuthenticationException e){
        Map<String, String> map = new HashMap<>();
        map.put("err", e.getMessage());
        return map;
    }




}

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

@Configuration
@ControllerAdvice
@RestController
public class GlobalExceptionHandler  {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleException (Exception e){
        Map<String, String> map = new HashMap<>();
        map.put("err", e.getMessage());
        return map;
    }

}

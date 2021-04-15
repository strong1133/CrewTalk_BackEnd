package com.hh99_crewtalk.crewtalk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @ResponseBody
    @GetMapping(value = "/api/hello", produces = "application/text")
    public String helloApi() {
        return "hello";
    }
}

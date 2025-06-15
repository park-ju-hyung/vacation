package com.example.vacation.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("💡 login controller 진입 확인!");
        return "login"; // login.html 템플릿 없으므로 500 발생 예상
    }

}

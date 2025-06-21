package com.example.vacation.mvc.controller.common;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    /** 로그인 **/
    @GetMapping("/")
    public String loginPage() {
        return "index";  // 이건 templates/login.html 을 의미함
    }



}

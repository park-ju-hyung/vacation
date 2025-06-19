package com.example.vacation.mvc.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformationController {

    /** 정보 수정 modify**/
    @GetMapping("/Information_modify")
    public String InformationModifyPage() {
        return "site/informodify/Information_modify";
    }



}

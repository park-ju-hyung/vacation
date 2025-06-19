package com.example.vacation.mvc.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserSpecialBreakController {

    /** 특별휴가 신청 list **/
    @GetMapping("/UserSpecialBreak_list")
    public String UserSpecialListPage() {
        return "site/UserSpecialBreak/UserSpecialBreak_list";
    }

    /** 특별휴가 신청 view **/
    @GetMapping("/UserSpecialBreak_view")
    public String UserSpecialViewPage() {
        return "site/UserSpecialBreak/UserSpecialBreak_view";
    }

    /** 특별휴가 신청 regist **/
    @GetMapping("/UserSpecialBreak_regist")
    public String UserSpecialRegistPage() {
        return "site/UserSpecialBreak/UserSpecialBreak_regist";
    }

    /** 특별휴가 신청 modify **/
    @GetMapping("/UserSpecialBreak_modify")
    public String UserSpecialModifyPage() {
        return "site/UserSpecialBreak/UserSpecialBreak_modify";
    }
    

}

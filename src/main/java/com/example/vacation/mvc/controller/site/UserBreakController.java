package com.example.vacation.mvc.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserBreakController {

    /** 휴가 신청 list**/
    @GetMapping("/site/UserBreak/Break_list")
    public String UserBreakListPage() {
        return "site/UserBreak/Break_list";
    }

    /** 휴가 신청 view **/
    @GetMapping("/site/UserBreak/Break_view")
    public String UserBreakViewPage() {
        return "site/UserBreak/Break_view";
    }

    /** 휴가 신청 regist **/
    @GetMapping("/site/UserBreak/Break_regist")
    public String UserBreakRegistPage() {
        return "site/UserBreak/Break_regist";
    }

    /** 휴가 신청 modify **/
    @GetMapping("/site/UserBreak/Break_modify")
    public String UserBreakModifyPage() {
        return "site/UserBreak/Break_modify";
    }



}

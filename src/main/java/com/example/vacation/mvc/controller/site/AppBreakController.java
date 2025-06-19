package com.example.vacation.mvc.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppBreakController {

    /** 휴가 신청 **/
    @GetMapping("/AppBreak_list")
    public String AppBreakListPage() {
        return "site/UserAppBreak/AppBreak_list";
    }

    /** 휴가 승인 **/
    @GetMapping("/AppBreak_view")
    public String AppBreakViewPage() {
        return "site/UserAppBreak/AppBreak_view";
    }



}

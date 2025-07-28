package com.example.vacation.mvc.controller.mngr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppBreakController {

    /** 휴가 신청 **/
    @GetMapping("/mngr/UserAppBreak/list")
    public String AppBreakListPage() {
        return "mngr/UserAppBreak/list";
    }

    /** 휴가 승인 **/
    @GetMapping("/mngr/UserAppBreak/view")
    public String AppBreakViewPage() {
        return "mngr/UserAppBreak/view";
    }



}

package com.example.vacation.mvc.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserSickBreakController {

    /** 병가 신청 list **/
    @GetMapping("/site/UserSickBreak/UserSickBreak_list")
    public String UserSickListPage() {
        return "site/UserSickBreak/UserSickBreak_list";
    }

    /** 병가 신청 view **/
    @GetMapping("/site/UserSickBreak/UserSickBreak_view")
    public String UserSickViewPage() {
        return "site/UserSickBreak/UserSickBreak_view";
    }

    /** 병가 신청 regist **/
    @GetMapping("/site/UserSickBreak/UserSickBreak_regist")
    public String UserSickRegistPage() {
        return "site/UserSickBreak/UserSickBreak_regist";
    }

    /** 병가 신청 modify **/
    @GetMapping("/site/UserSickBreak/UserSickBreak_modify")
    public String UserSickModifyPage() {
        return "site/UserSickBreak/UserSickBreak_modify";
    }
    

}

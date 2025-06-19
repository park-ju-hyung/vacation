package com.example.vacation.mvc.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserBreakListController {

    /** 연차현황조회 list**/
    @GetMapping("/UserBreakList_list")
    public String BreakListPage() {
        return "site/UserBreakList/UserBreakList_list";
    }

    /** 연차현황조회 view **/
    @GetMapping("/UserBreakList_view")
    public String BreakViewPage() {
        return "site/UserBreakList/UserBreakList_view";
    }


}

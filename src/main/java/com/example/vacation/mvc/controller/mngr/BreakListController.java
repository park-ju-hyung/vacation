package com.example.vacation.mvc.controller.mngr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BreakListController {

    /**연차현황 조회 리스트 페이지**/
    @GetMapping("/BreakList_list")
    public String BreakListPage() {
        return "mngr/BreakList/Break_list";
    }


    /**연차현황 조회 상세 페이지**/
}

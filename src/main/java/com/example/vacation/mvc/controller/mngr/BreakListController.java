package com.example.vacation.mvc.controller.mngr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BreakListController {

    /**연차현황 조회 list**/
    @GetMapping("mngr/BreakList/BreakList_list")
    public String MngrBreakListPage() {
        return "mngr/BreakList/Break_list";
    }

    /**연차현황 조회 view**/
    @GetMapping("mngr/BreakList/BreakList_view")
    public String MngrBreakViewPage() {
        return "mngr/BreakList/Break_view";
    }

}

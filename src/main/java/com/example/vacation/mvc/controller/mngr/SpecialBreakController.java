package com.example.vacation.mvc.controller.mngr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpecialBreakController {

    /**특별휴가 list**/
    @GetMapping("mngr/specialBreak/specialBreak_list")
    public String SpecialBreakListPage() {
        return "mngr/specialBreak/specialBreak_list";
    }

    /**특별휴가 view**/
    @GetMapping("mngr/specialBreak/specialBreak_view")
    public String SpecialBreakViewPage() {
        return "mngr/specialBreak/specialBreak_view";
    }

}

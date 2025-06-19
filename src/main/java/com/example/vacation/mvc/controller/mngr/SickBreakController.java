package com.example.vacation.mvc.controller.mngr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SickBreakController {

    /**병가 list**/
    @GetMapping("/sickBreak_list")
    public String SickBreakListPage() {
        return "mngr/sickBreak/sickBreak_list";
    }

    /**병가 view**/
    @GetMapping("/sickBreak_view")
    public String SickBreakViewPage() {
        return "mngr/sickBreak/sickBreak_view";
    }

}

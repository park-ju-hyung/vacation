package com.example.vacation.mvc.controller.mngr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RetireController {

    /**퇴직자 관리 list**/
    @GetMapping("/mngr/retire/retired_list")
    public String RetiredListPage() {
        return "mngr/retire/retired_list";
    }

    /**퇴직자 관리 view**/
    @GetMapping("/mngr/retire/retired_view")
    public String RetiredViewPage() {
        return "mngr/retire/retired_view";
    }

}

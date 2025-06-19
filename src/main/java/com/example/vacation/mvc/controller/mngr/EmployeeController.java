package com.example.vacation.mvc.controller.mngr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    /**직원 관리 및 등록 list**/
    @GetMapping("/Employee_Registration_list")
    public String EmployeeListPage() {
        return "mngr/Employee/Employee_Registration_list";
    }

    /**직원 관리 및 등록 view**/
    @GetMapping("/Employee_Registration_view")
    public String EmployeeViewPage() {
        return "mngr/Employee/Employee_Registration_view";
    }

    /**직원 관리 및 등록 regist**/
    @GetMapping("/Employee_Registration_regist")
    public String EmployeeRegistPage() {
        return "mngr/Employee/Employee_Registration_regist";
    }

    /**직원 관리 및 등록 modify**/
    @GetMapping("/Employee_Registration_modify")
    public String EmployeeModifyPage() {
        return "mngr/Employee/Employee_Registration_modify";
    }


    /**연차현황 조회 상세 페이지**/
}

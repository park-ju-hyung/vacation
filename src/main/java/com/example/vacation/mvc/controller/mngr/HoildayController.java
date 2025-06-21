package com.example.vacation.mvc.controller.mngr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HoildayController {

    /**공휴일 관리 list**/
    @GetMapping("mngr/holiday/holiday_list")
    public String HolidayListPage() {
        return "mngr/holiday/holiday_list";
    }

    /**공휴일 관리 view**/
    @GetMapping("mngr/holiday/holiday_view")
    public String HolidayViewPage() {
        return "mngr/holiday/holiday_view";
    }

    /**공휴일 관리 regist**/
    @GetMapping("mngr/holiday/holiday_regist")
    public String HolidayRegistPage() {
        return "mngr/holiday/holiday_regist";
    }

    /**공휴일 관리 modify**/
    @GetMapping("mngr/holiday/holiday_modify")
    public String HolidayModifyPage() {
        return "mngr/holiday/holiday_modify";
    }


    /**연차현황 조회 상세 페이지**/
}

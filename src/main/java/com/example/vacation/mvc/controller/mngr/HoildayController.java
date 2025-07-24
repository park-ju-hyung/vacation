package com.example.vacation.mvc.controller.mngr;

import com.example.vacation.mvc.dto.HolidayDTO;
import com.example.vacation.mvc.service.mngr.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HoildayController {
    private final HolidayService HolidayService;


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




    @ResponseBody
    @PostMapping("/holidayList")
    public ResponseEntity<?> holidayList(@RequestBody HolidayDTO holidaydto) throws Exception {
        return new ResponseEntity<>(HolidayService.selectHolidayList(holidaydto), HttpStatus.OK);
    }
}

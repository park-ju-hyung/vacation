package com.example.vacation.mvc.controller.site;

import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.SickBreakDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.service.site.BreakService;
import com.example.vacation.mvc.service.site.SickBreakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserSickBreakController {

    private final SickBreakService sickBreakService;

    /** 병가 신청 regist **/
    @GetMapping("/site/UserSickBreak/regist")
    public String UserSickRegistPage(EmployeeDTO employeedto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeedto);
        modelMap.addAttribute("employeeVO", sickBreakService.view(employeedto));
        return "site/UserSickBreak/regist";
    }

    @PostMapping("/insertSickBreak")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insertSickBreak(@RequestBody SickBreakDTO sickbreakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();
        sickBreakService.insertSickBreak(sickbreakdto);
        rs.put("result", "SUCCESS");
        return ResponseEntity.ok(rs);
    }





    /** 병가 신청 list **/
    @GetMapping("/site/UserSickBreak/list")
    public String UserSickListPage(SickBreakDTO Sickbreakdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("SickBreakDTO", Sickbreakdto);
        return "site/UserSickBreak/list";
    }

    @PostMapping("/site/UserSickBreak/list")
    @ResponseBody
    public ResponseEntity<?> list(@RequestBody SickBreakDTO Sickbreakdto) throws Exception {
        return new ResponseEntity<>(sickBreakService.SickBreakList(Sickbreakdto), HttpStatus.OK);
    }

    /** 병가 신청 view **/
    @GetMapping("/site/UserSickBreak/view")
    public String UserSickViewPage() {
        return "site/UserSickBreak/view";
    }



    /** 병가 신청 modify **/
    @GetMapping("/site/UserSickBreak/UserSickBreak_modify")
    public String UserSickModifyPage() {
        return "site/UserSickBreak/modify";
    }

    /** 휴가 삭제 **/
    @PostMapping("/SickBreakDelete")
    @ResponseBody
    public ResponseEntity<?> SickBreakDelete(@RequestBody SickBreakDTO Sickbreakdto, ModelMap modelMap) throws Exception {
        return new ResponseEntity<>(sickBreakService.SickBreakDelete(Sickbreakdto), HttpStatus.OK);
    }
    

}

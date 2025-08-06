package com.example.vacation.mvc.controller.site;

import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.service.site.BreakService;
import com.example.vacation.mvc.service.site.SpecialBreakService;
import lombok.RequiredArgsConstructor;
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
public class UserSpecialBreakController {

    private final SpecialBreakService specialBreakService;

    /** 특별휴가 신청 regist **/
    @GetMapping("/site/UserSpecialBreak/regist")
    public String UserSpecialRegistPage(EmployeeDTO employeedto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeedto);
        modelMap.addAttribute("employeeVO", specialBreakService.view(employeedto));
        return "site/UserSpecialBreak/regist";
    }

    @PostMapping("/insertSpecialBreak")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insertSpecialBreak(@RequestBody SpecialBreakDTO Specialbreakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();
        specialBreakService.insertSpecialBreak(Specialbreakdto);
        rs.put("result", "SUCCESS");
        return ResponseEntity.ok(rs);
    }



    /** 특별휴가 신청 list **/
    @GetMapping("/site/UserSpecialBreak/list")
    public String UserSpecialListPage() {
        return "site/UserSpecialBreak/list";
    }

    /** 특별휴가 신청 view **/
    @GetMapping("/site/UserSpecialBreak/view")
    public String UserSpecialViewPage() {
        return "site/UserSpecialBreak/view";
    }



    /** 특별휴가 신청 modify **/
    @GetMapping("/site/UserSpecialBreak/modify")
    public String UserSpecialModifyPage() {
        return "site/UserSpecialBreak/modify";
    }
    

}

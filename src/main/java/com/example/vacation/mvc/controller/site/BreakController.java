package com.example.vacation.mvc.controller.site;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.mapper.BreakMapper;
import com.example.vacation.mvc.service.mngr.BreakService;
import com.example.vacation.mvc.service.mngr.EmployeeService;
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
public class BreakController {

    private final BreakService breakService;

    /** 휴가 신청 regist **/
    @GetMapping("/site/UserBreak/regist")
    public String UserBreakRegistPage(EmployeeDTO employeedto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeedto);
        modelMap.addAttribute("employeeVO", breakService.view(employeedto));
        return "site/UserBreak/regist";
    }

    @PostMapping("/insertBreak")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insertBreak(@RequestBody BreakDTO breakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();
        breakService.InsertBreak(breakdto);
        rs.put("result", "SUCCESS");
        return ResponseEntity.ok(rs);
    }















}

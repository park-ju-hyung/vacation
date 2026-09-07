package com.example.vacation.mvc.controller.admin;

import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.service.admin.EmployeeService;
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
public class SpecialController {

    private final EmployeeService employeeService;

    /**특별휴가 현황 list**/
    @GetMapping("/admin/special/list")
    public String EmployeeListPage(EmployeeDTO employeedto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeedto);
        return "admin/special/list";
    }







}

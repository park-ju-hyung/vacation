package com.example.vacation.mvc.controller.admin;

import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.service.admin.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SickController {

    private final EmployeeService employeeService;

    /**병가 list**/
    @GetMapping("/admin/sick/list")
    public String EmployeeListPage(EmployeeDTO employeedto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeedto);
        return "admin/sick/list";
    }





}

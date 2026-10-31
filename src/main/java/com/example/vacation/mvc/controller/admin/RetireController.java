package com.example.vacation.mvc.controller.admin;

import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.mapper.admin.RetireMapper;
import com.example.vacation.mvc.service.admin.EmployeeService;
import com.example.vacation.mvc.service.admin.RetireService;
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
public class RetireController {

    private final RetireService retireService;

    /**퇴직자 list**/
    @GetMapping("/admin/retire/list")
    public String RetireListPage(EmployeeStatusDTO employeestatusdto, ModelMap modelMap) throws Exception {
        System.out.println("retire");
        modelMap.addAttribute("EmployeeStatusDTO", employeestatusdto);
        return "admin/retire/list";
    }

    @PostMapping("/admin/retireStatus/list")
    @ResponseBody
    public ResponseEntity<?> RetireStatusList(@RequestBody EmployeeStatusDTO employeestatusdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("EmployeeStatusDTO", employeestatusdto);
        return new ResponseEntity<>(retireService.RetireStatusList(employeestatusdto), HttpStatus.OK);
    }

    /**상세보기**/
    @GetMapping("/admin/retire/view")
    public String RetireViewPage(EmployeeStatusDTO employeestatusdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeStatusDTO", employeestatusdto);
        modelMap.addAttribute("employeeStatusVO", retireService.view(employeestatusdto));
        System.out.println(employeestatusdto.getPosition());
        return "admin/retire/view";
    }


}

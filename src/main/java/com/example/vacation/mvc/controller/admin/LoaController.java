package com.example.vacation.mvc.controller.admin;

import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.service.admin.LoaService;
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

@Controller
@RequiredArgsConstructor
public class LoaController {

    private final LoaService loaService;

    /**퇴직자 list**/
    @GetMapping("/admin/loa/list")
    public String RetireListPage(EmployeeStatusDTO employeestatusdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("EmployeeStatusDTO", employeestatusdto);
        return "admin/loa/list";
    }

    @PostMapping("/admin/loaStatus/list")
    @ResponseBody
    public ResponseEntity<?> RetireStatusList(@RequestBody EmployeeStatusDTO employeestatusdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("EmployeeStatusDTO", employeestatusdto);
        return new ResponseEntity<>(loaService.LoaStatusList(employeestatusdto), HttpStatus.OK);
    }

    /**상세보기**/
    @GetMapping("/admin/loa/view")
    public String RetireViewPage(EmployeeStatusDTO employeestatusdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeStatusDTO", employeestatusdto);
        modelMap.addAttribute("employeeStatusVO", loaService.view(employeestatusdto));
        System.out.println(employeestatusdto.getPosition());
        return "admin/loa/view";
    }


}

package com.example.vacation.mvc.controller.site;

import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.service.mngr.EmployeeService;
import com.example.vacation.mvc.service.mngr.InformationService;
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
public class InformationController {

    private final InformationService informationService;

    /** 정보 수정 modify**/

    @GetMapping("/site/informodify/modify")
    public String EmployeeModifyPage(EmployeeDTO employeedto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeedto);
        modelMap.addAttribute("employeeVO", informationService.view(employeedto));
        return "site/informodify/modify";
    }

    @PostMapping("/informodify/modify")
    @ResponseBody
    public ResponseEntity<?> modify(@RequestBody EmployeeDTO employeedto) throws Exception {
        return new ResponseEntity<>(informationService.modify(employeedto), HttpStatus.OK);
    }



}

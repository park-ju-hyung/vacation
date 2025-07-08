package com.example.vacation.mvc.controller.mngr;

import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.service.mngr.EmployeeService;
import com.example.vacation.mvc.service.mngr.RetireService;
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

    /**퇴직자 관리 list**/
    @GetMapping("/mngr/retire/list")
    public String RetiredListPage(EmployeeDTO employeeDTO, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeeDTO);
        return "mngr/retire/list";
    }

    @PostMapping("/mngr/retire/list")
    @ResponseBody
    public ResponseEntity<?> list(@RequestBody EmployeeDTO employeeDTO) throws Exception {
        return new ResponseEntity<>(retireService.retirelist(employeeDTO), HttpStatus.OK);
    }

    /**퇴직자 관리 view**/
    @GetMapping("/mngr/retire/view")
    public String RetiredViewPage(EmployeeDTO employeeDTO, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeeDTO);
        modelMap.addAttribute("employeeVO", retireService.view(employeeDTO));
        return "mngr/retire/view";
    }

}

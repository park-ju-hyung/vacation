package com.example.vacation.mvc.controller.mngr;

import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.mapper.EmployeeMapper;
import com.example.vacation.mvc.service.mngr.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    /**직원 관리 및 등록 list**/
    @GetMapping("/mngr/Employee/list")
    public String EmployeeListPage() {
        return "mngr/Employee/list";
    }

    /**직원 관리 및 등록 view**/
    @GetMapping("/mngr/Employee/view")
    public String EmployeeViewPage() {
        return "mngr/Employee/view";
    }

    /**직원 관리 및 등록 regist**/
    @GetMapping("/mngr/Employee/regist")
    public String EmployeeRegistPage(EmployeeDTO employeeDTO, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeeDTO);
        return "mngr/Employee/regist";
    }

    @PostMapping("/employee/regist")
    @ResponseBody
    public ResponseEntity<?> regist(@RequestBody EmployeeDTO employeeDTO) throws Exception {
        return ResponseEntity.ok(employeeService.regist(employeeDTO));
    }


    /**직원 관리 및 등록 modify**/
    @GetMapping("/mngr/Employee/modify")
    public String EmployeeModifyPage() {
        return "mngr/Employee/modify";
    }


    /**연차현황 조회 상세 페이지**/
}

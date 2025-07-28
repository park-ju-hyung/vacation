package com.example.vacation.mvc.controller.mngr;

import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.mapper.EmployeeMapper;
import com.example.vacation.mvc.service.mngr.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    /**직원 관리 및 등록 regist**/
    @GetMapping("/admin/Employee/regist")
    public String EmployeeRegistPage(EmployeeDTO employeedto, ModelMap modelMap) throws Exception {
        System.out.println("empBirth 값 확인2: " + employeedto.getEmpBirth());
        modelMap.addAttribute("employeeDTO", employeedto);
        return "admin/Employee/regist";
    }

    @PostMapping("/employee/regist")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> regist(@RequestBody EmployeeDTO employeedto) throws Exception {
        Map<String, Object> rs = new HashMap<>();
        employeeService.regist(employeedto);
        rs.put("result", "SUCCESS");
        return ResponseEntity.ok(rs);
    }

    /**직원 관리 및 등록 list**/
    @GetMapping("/admin/Employee/list")
    public String EmployeeListPage(EmployeeDTO employeedto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeedto);
        return "admin/Employee/list";
    }

    @PostMapping("/admin/Employee/list")
    @ResponseBody
    public ResponseEntity<?> list(@RequestBody EmployeeDTO employeedto) throws Exception {
        return new ResponseEntity<>(employeeService.Employeelist(employeedto), HttpStatus.OK);
    }

    @PostMapping("/admin/EmployeeStatus/list")
    @ResponseBody
    public ResponseEntity<?> StatusList(@RequestBody EmployeeStatusDTO employeestatusdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeestatusDTO", employeestatusdto);
        return new ResponseEntity<>(employeeService.EmployeeStatusList(employeestatusdto), HttpStatus.OK);
    }

    /**직원 관리 및 등록 modify**/
    @GetMapping("/admin/Employee/modify")
    public String EmployeeModifyPage(EmployeeDTO employeedto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeedto);
        modelMap.addAttribute("employeeVO", employeeService.view(employeedto));
        return "admin/Employee/modify";
    }

    @PostMapping("/Employee/modify")
    @ResponseBody
    public ResponseEntity<?> modify(@RequestBody EmployeeFormDTO employeeformdto) throws Exception {
        return new ResponseEntity<>(employeeService.modify(employeeformdto), HttpStatus.OK);
    }

    /**직원 관리 및 등록 view**/
    @GetMapping("/admin/Employee/view")
    public String EmployeeViewPage(EmployeeDTO employeedto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employeeDTO", employeedto);
        modelMap.addAttribute("employeeDTO", employeedto);
        modelMap.addAttribute("employeeVO", employeeService.view(employeedto));
        return "admin/Employee/view";
    }







}

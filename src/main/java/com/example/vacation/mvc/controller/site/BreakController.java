package com.example.vacation.mvc.controller.site;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.service.mngr.BreakService;
import jakarta.servlet.http.HttpSession;
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
        breakService.insertBreak(breakdto);
        rs.put("result", "SUCCESS");
        return ResponseEntity.ok(rs);
    }

    /** 총 사용휴가일수 가져오기 **/
    @GetMapping("/getBreakTotalDay")
    @ResponseBody
    public Map<String, Object> getBreakTotalDay(HttpSession session) throws Exception {
        String empNo = (String) session.getAttribute(SessionConstant.SESSION_MANAGER_ID);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empNo", empNo);

        return breakService.getBreakTotalDay(paramMap);
    }


    /** 휴가 신청 list **/
    @GetMapping("/site/UserBreak/list")
    public String BreakListPage(BreakDTO breakdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("breakDTO", breakdto);
        return "site/UserBreak/list";
    }

    @PostMapping("/UserBreak/list")
    @ResponseBody
    public ResponseEntity<?> list(@RequestBody BreakDTO breakdto) throws Exception {
        return new ResponseEntity<>(breakService.Breaklist(breakdto), HttpStatus.OK);
    }

    /** 상세보기 **/
    @GetMapping("/site/UserBreak/view")
    public String BreakViewPage(BreakDTO breakdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("breakDTO", breakdto);
        modelMap.addAttribute("breakVO", breakService.BreakView(breakdto));
        System.out.println("controller: " + breakService.BreakView(breakdto));
        return "site/UserBreak/view";
    }


















}

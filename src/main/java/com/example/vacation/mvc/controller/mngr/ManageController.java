package com.example.vacation.mvc.controller.mngr;

import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.service.mngr.ManageService;
import com.example.vacation.mvc.vo.BreakVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ManageController {

    private final ManageService manageService;

    /** 휴가 승인 list **/
    @GetMapping("/mngr/manage/list")
    public String ManageListPage(BreakDTO breakdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("breakDTO", breakdto);
        return "mngr/manage/list";
    }

    @PostMapping("/manage/list")
    @ResponseBody
    public ResponseEntity<?> list(@RequestBody BreakDTO breakdto) throws Exception {
        return new ResponseEntity<>(manageService.manageList(breakdto), HttpStatus.OK);
    }

    /** 휴가 승인 view**/
    @GetMapping("/mngr/manage/view")
    public String ManageViewPage(BreakDTO breakdto, ModelMap modelMap) throws Exception {
        BreakVO breakvo = manageService.ManageView(breakdto);

        modelMap.addAttribute("breakDTO", breakdto);
        modelMap.addAttribute("breakVO", breakvo);
        System.out.println("controller: " + breakvo.getRemainDays());

        return "mngr/manage/view";
    }

    @ResponseBody
    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody BreakDTO breakdto, ModelMap modelMap) throws Exception {
        return new ResponseEntity<>(manageService.updateStatus(breakdto), HttpStatus.OK);
    }





}

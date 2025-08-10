package com.example.vacation.mvc.controller.mngr;

import com.example.vacation.mvc.dto.SickBreakDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.service.mngr.ManageSickService;
import com.example.vacation.mvc.service.mngr.ManageSpecialService;
import com.example.vacation.mvc.vo.SickBreakVO;
import com.example.vacation.mvc.vo.SpecialBreakVO;
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
public class ManageSickController {

    private final ManageSickService manageSickService;

    /**특별휴가 list**/
    @GetMapping("/mngr/ManageSick/list")
    public String SickBreakListPage(SickBreakDTO Sickbreakdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("SickBreakDTO", Sickbreakdto);
        return "mngr/ManageSick/list";
    }

    @PostMapping("/mngr/ManageSick/list")
    @ResponseBody
    public ResponseEntity<?> list(@RequestBody SickBreakDTO Sickbreakdto) throws Exception {
        return new ResponseEntity<>(manageSickService.SickBreakList(Sickbreakdto), HttpStatus.OK);
    }

    /**병가관리 view**/
    @GetMapping("/mngr/ManageSick/view")
    public String SickBreakViewPage(SickBreakDTO Sickbreakdto, ModelMap modelMap) throws Exception {
        SickBreakVO sickBreakVO = manageSickService.SickBreakView(Sickbreakdto);
        modelMap.addAttribute("SickBreakDTO", Sickbreakdto);
        modelMap.addAttribute("SickBreakVO", sickBreakVO);
        return "mngr/ManageSick/view";
    }

    @ResponseBody
    @PostMapping("/ManageSick")
    public ResponseEntity<?> ManageSick(@RequestBody SickBreakDTO Sickbreakdto, ModelMap modelMap) throws Exception {
        return new ResponseEntity<>(manageSickService.updateStatus(Sickbreakdto), HttpStatus.OK);
    }


}

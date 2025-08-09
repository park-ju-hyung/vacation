package com.example.vacation.mvc.controller.mngr;

import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.service.mngr.ManageSpecialService;
import com.example.vacation.mvc.service.site.SpecialBreakService;
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
public class ManageSpecialController {

    private final ManageSpecialService manageSpecialService;

    /**특별휴가 list**/
    @GetMapping("/mngr/ManageSpecial/list")
    public String SpecialBreakListPage(SpecialBreakDTO Specialbreakdto, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("SpecialBreakDTO", Specialbreakdto);
        return "mngr/ManageSpecial/list";
    }

    @PostMapping("/mngr/ManageSpecial/list")
    @ResponseBody
    public ResponseEntity<?> list(@RequestBody SpecialBreakDTO Specialbreakdto) throws Exception {
        return new ResponseEntity<>(manageSpecialService.SpecialBreakList(Specialbreakdto), HttpStatus.OK);
    }

    /**특별휴가 view**/
    @GetMapping("/mngr/ManageSpecial/view")
    public String SpecialBreakViewPage(SpecialBreakDTO Specialbreakdto, ModelMap modelMap) throws Exception {
        SpecialBreakVO specialBreakVO = manageSpecialService.SpecialBreakView(Specialbreakdto);
        modelMap.addAttribute("SpecialBreakDTO", Specialbreakdto);
        modelMap.addAttribute("SpecialBreakVO", specialBreakVO);
        return "mngr/ManageSpecial/view";
    }

    @ResponseBody
    @PostMapping("/ManageSpecial")
    public ResponseEntity<?> ManageSpecial(@RequestBody SpecialBreakDTO Specialbreakdto, ModelMap modelMap) throws Exception {
        return new ResponseEntity<>(manageSpecialService.updateStatus(Specialbreakdto), HttpStatus.OK);
    }


}

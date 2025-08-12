package com.example.vacation.mvc.controller.site;

import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.BreakListDTO;
import com.example.vacation.mvc.service.site.BreakListService;
import com.example.vacation.mvc.service.site.BreakService;
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
public class UserBreakListController {

    private final BreakListService breakListService;

    /** 연차현황조회 list**/
    @GetMapping("/site/UserBreakList/list")
    public String BreakListPage(BreakListDTO breakListDTO, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("BreakListDTO", breakListDTO);
        return "site/UserBreakList/list";
    }

    @PostMapping("/site/UserBreakList/list")
    @ResponseBody
    public ResponseEntity<?> list(@RequestBody BreakListDTO breakListDTO) throws Exception {
        return new ResponseEntity<>(breakListService.AllBreakList(breakListDTO), HttpStatus.OK);
    }

    /** 연차현황조회 view **/
    @GetMapping("/site/UserBreakList/UserBreakList_view")
    public String BreakViewPage() {
        return "site/UserBreakList/UserBreakList_view";
    }


}

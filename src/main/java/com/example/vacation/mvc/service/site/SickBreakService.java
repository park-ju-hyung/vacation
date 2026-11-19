package com.example.vacation.mvc.service.site;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.SickBreakDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.mapper.site.BreakMapper;
import com.example.vacation.mvc.mapper.site.SickBreakMapper;
import com.example.vacation.mvc.mapper.site.SpecialBreakMapper;
import com.example.vacation.mvc.vo.BreakVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import com.example.vacation.mvc.vo.SickBreakVO;
import com.example.vacation.mvc.vo.SpecialBreakVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SickBreakService {

    private final SickBreakMapper sickBreakMapper;

    // 병가 신청
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Map<String, Object> insertSickBreak(BreakDTO breakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        // 로그인한 empNO 정보 가져오기
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);

        EmployeeDTO empDto = new EmployeeDTO();
        empDto.setEmpNo(empNo);
        EmployeeVO employee = sickBreakMapper.employeeVO(empDto);
        System.out.println("employeeVo: " + employee);

        // 직원 정보 가져오기
        breakdto.setEmpNo(employee.getEmpNo());
        breakdto.setEmpName(employee.getEmpName());
        breakdto.setEmpBirth(employee.getEmpBirth());
        breakdto.setPosition(employee.getPosition());
        breakdto.setDepartment(employee.getDepartment());
        breakdto.setVacationType("병가");
        System.out.println("employeeVo: " + employee.getEmpNo());
        System.out.println("부여휴가일수 " + employee.getTotalDays());

        sickBreakMapper.insertSickBreak(breakdto);

        rs.put("result", "SUCCESS");
        return rs;
    }

    //list
    @Transactional(readOnly = false)
    public Map<String, Object> SickBreakList(BreakDTO breakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        // 로그인한 empNO 정보 가져오기
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);
        breakdto.setEmpNo(empNo);


        int pageNo = breakdto.getPageNo() == 0 ? 1 : breakdto.getPageNo();
        int pageSize = breakdto.getPageSize() == 0 ? 10 : breakdto.getPageSize();
        int pageBlock = breakdto.getPageBlock() == 0 ? 10 : breakdto.getPageBlock();
        breakdto.setPageNo(pageNo);
        breakdto.setPageSize(pageSize);
        breakdto.setPageBlock(pageBlock);
        breakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<SickBreakVO> list = sickBreakMapper.SickBreakList(breakdto);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            breakdto.setPageNo(pageNo);
            breakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = sickBreakMapper.SickBreakList(breakdto);
        }

        int totalCount = sickBreakMapper.SickBreakCount(breakdto);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("BreakDTO", breakdto);
        rs.put("list", list);
        rs.put("totalCount", totalCount);
        rs.put("totalPageNo", totalPageNo);
        rs.put("pagingHTML", pagingHTML);

        return rs;
    }

    // 신청자 정보 가져오기
    @Transactional(readOnly = true)
    public EmployeeVO view(EmployeeDTO employeedto) throws Exception {
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);
        employeedto.setEmpNo(empNo);
        System.out.println("view empNo: " + empNo);
        return sickBreakMapper.employeeVO(employeedto);
    }

    // 신청자 정보 + 휴가 데이터
    @Transactional(readOnly = true)
    public BreakVO SickBreakView(BreakDTO breakdto) throws Exception {
        BreakVO breakvo = sickBreakMapper.breakVO(breakdto);
        System.out.println("breakvo: " + breakvo.getEmpName());
        return breakvo;

    }

    // 특별휴가 삭제
    public Map<String, Object> SickBreakDelete(SickBreakDTO sickbreakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();
        sickBreakMapper.SickBreakDelete(sickbreakdto);
        rs.put("result", true);
        return rs;
    }

}

package com.example.vacation.mvc.service.site;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.mapper.BreakMapper;
import com.example.vacation.mvc.mapper.site.SpecialBreakMapper;
import com.example.vacation.mvc.vo.BreakVO;
import com.example.vacation.mvc.vo.EmployeeVO;
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
public class SpecialBreakService {

    private final SpecialBreakMapper specialBreakMapper;

    // 특별휴가 신청
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Map<String, Object> insertSpecialBreak(SpecialBreakDTO Specialbreakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        // 로그인한 empNO 정보 가져오기
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);

        EmployeeDTO empDto = new EmployeeDTO();
        empDto.setEmpNo(empNo);
        EmployeeVO employee = specialBreakMapper.employeeVO(empDto);
        System.out.println("employeeVo: " + employee);

        // 직원 정보 가져오기
        Specialbreakdto.setEmpNo(employee.getEmpNo());
        Specialbreakdto.setEmpName(employee.getEmpName());
        Specialbreakdto.setEmpBirth(employee.getEmpBirth());
        Specialbreakdto.setPosition(employee.getPosition());
        Specialbreakdto.setDepartment(employee.getDepartment());
        System.out.println("employeeVo: " + employee.getEmpNo());
        System.out.println("부여휴가일수 " + employee.getTotalDays());

        specialBreakMapper.insertSpecialBreak(Specialbreakdto);

        rs.put("result", "SUCCESS");
        return rs;
    }

    //list
    @Transactional(readOnly = false)
    public Map<String, Object> SpecialBreakList(SpecialBreakDTO Specialbreakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        // 로그인한 empNO 정보 가져오기
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);
        Specialbreakdto.setEmpNo(empNo);


        int pageNo = Specialbreakdto.getPageNo() == 0 ? 1 : Specialbreakdto.getPageNo();
        int pageSize = Specialbreakdto.getPageSize() == 0 ? 10 : Specialbreakdto.getPageSize();
        int pageBlock = Specialbreakdto.getPageBlock() == 0 ? 10 : Specialbreakdto.getPageBlock();
        Specialbreakdto.setPageNo(pageNo);
        Specialbreakdto.setPageSize(pageSize);
        Specialbreakdto.setPageBlock(pageBlock);
        Specialbreakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<SpecialBreakVO> list = specialBreakMapper.SpecialBreakList(Specialbreakdto);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            Specialbreakdto.setPageNo(pageNo);
            Specialbreakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = specialBreakMapper.SpecialBreakList(Specialbreakdto);
        }

        int totalCount = specialBreakMapper.SpecialBreakCount(Specialbreakdto);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("SpecialBreakDTO", Specialbreakdto);
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
        return specialBreakMapper.employeeVO(employeedto);
    }

    // 신청자 정보 + 휴가 데이터
    @Transactional(readOnly = true)
    public SpecialBreakVO SpecialBreakView(SpecialBreakDTO Specialbreakdto) throws Exception {
        return specialBreakMapper.SpecialBreakVO(Specialbreakdto);
    }

    // 특별휴가 삭제
    public Map<String, Object> SpecialBreakDelete(SpecialBreakDTO Specialbreakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();
        specialBreakMapper.SpecialBreakDelete(Specialbreakdto);
        rs.put("result", true);
        return rs;
    }








}

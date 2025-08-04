package com.example.vacation.mvc.service.mngr;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.dto.*;
import com.example.vacation.mvc.mapper.BreakMapper;
import com.example.vacation.mvc.mapper.EmployeeMapper;
import com.example.vacation.mvc.mapper.InformationMapper;
import com.example.vacation.mvc.vo.BreakVO;
import com.example.vacation.mvc.vo.EmployeeStatusVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BreakService {

    private final BreakMapper breakMapper;

    //list
    @Transactional(readOnly = false)
    public Map<String, Object> Breaklist(BreakDTO breakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        int pageNo = breakdto.getPageNo() == 0 ? 1 : breakdto.getPageNo();
        int pageSize = breakdto.getPageSize() == 0 ? 10 : breakdto.getPageSize();
        int pageBlock = breakdto.getPageBlock() == 0 ? 10 : breakdto.getPageBlock();
        breakdto.setPageNo(pageNo);
        breakdto.setPageSize(pageSize);
        breakdto.setPageBlock(pageBlock);
        breakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<BreakVO> list = breakMapper.breaklist(breakdto);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            breakdto.setPageNo(pageNo);
            breakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = breakMapper.breaklist(breakdto);
        }

        int totalCount = breakMapper.breakCount(breakdto);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("breakDTO", breakdto);
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
        return breakMapper.employeeVO(employeedto);
    }

    // 휴가 신청
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Map<String, Object> insertBreak(BreakDTO breakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        // 로그인한 empNO 정보 가져오기
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);

        EmployeeDTO empDto = new EmployeeDTO();
        empDto.setEmpNo(empNo);
        EmployeeVO employee = breakMapper.employeeVO(empDto);
        System.out.println("employeeVo: " + employee);

        // 직원 정보 가져오기
        breakdto.setEmpNo(employee.getEmpNo());
        breakdto.setEmpName(employee.getEmpName());
        breakdto.setEmpBirth(employee.getEmpBirth());
        breakdto.setPosition(employee.getPosition());
        breakdto.setDepartment(employee.getDepartment());
        breakdto.setTotalDays(employee.getTotalDays());
        System.out.println("employeeVo: " + employee.getEmpNo());
        System.out.println("부여휴가일수 " + employee.getTotalDays());

        // 총 휴가 사용일수
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empNo", empNo);
        Map<String, Object> TotalUseDays = getBreakTotalDay(paramMap);
        System.out.println("휴가 총 사용일수: " + TotalUseDays);

        breakMapper.insertBreak(breakdto);

        rs.put("result", "SUCCESS");
        return rs;
    }

    // 총 휴가일수 구하기
    public Map<String, Object> getBreakTotalDay(Map<String, Object> paramMap) throws Exception {
        return breakMapper.getBreakTotalDay(paramMap); // 이건 SUM(useDays)
    }

    // 신청자 정보 + 휴가 데이터
    @Transactional(readOnly = true)
    public BreakVO BreakView(BreakDTO breakdto) throws Exception {
        BreakVO breakvo = breakMapper.breakVO(breakdto);


        BigDecimal totalDays = breakvo.getTotalDays();
        BigDecimal useDays = breakvo.getUseDays();
        BigDecimal remainDay = totalDays.subtract(useDays);
        BigDecimal result;
        if (remainDay.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
            result = new BigDecimal(remainDay.intValue());
        } else {
            result = remainDay;
        }

        breakvo.setRemainDays(result); // ✅ 안전하게 저장됨

        System.out.println("totalDays: " + totalDays);
        System.out.println("useDays: " + useDays);
        System.out.println("remainDay: " + remainDay);
        System.out.println("remainDays: " + result);

        return breakvo;

    }

    //휴가 삭제
    public Map<String, Object> breakDelete(BreakDTO breakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();
        breakMapper.breakDelete(breakdto);
        rs.put("result", true);
        return rs;
    }






}

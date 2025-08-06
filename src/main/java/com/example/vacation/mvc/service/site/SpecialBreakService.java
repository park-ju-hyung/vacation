package com.example.vacation.mvc.service.site;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.mapper.BreakMapper;
import com.example.vacation.mvc.mapper.SpecialBreakMapper;
import com.example.vacation.mvc.vo.BreakVO;
import com.example.vacation.mvc.vo.EmployeeVO;
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

    // 신청자 정보 가져오기
    @Transactional(readOnly = true)
    public EmployeeVO view(EmployeeDTO employeedto) throws Exception {
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);
        employeedto.setEmpNo(empNo);
        System.out.println("view empNo: " + empNo);
        return specialBreakMapper.employeeVO(employeedto);
    }

    // 휴가 신청
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







}

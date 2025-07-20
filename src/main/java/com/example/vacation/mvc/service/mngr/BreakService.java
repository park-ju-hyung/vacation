package com.example.vacation.mvc.service.mngr;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.mapper.BreakMapper;
import com.example.vacation.mvc.mapper.EmployeeMapper;
import com.example.vacation.mvc.mapper.InformationMapper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class BreakService {

    private final BreakMapper breakMapper;

    // 직원 정보 가져오기
    @Transactional(readOnly = true)
    public EmployeeVO view(EmployeeDTO employeedto) throws Exception {
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);
        employeedto.setEmpNo(empNo);
        System.out.println("view empNo: " + empNo);
        return breakMapper.employeeVO(employeedto);
    }

    // 휴가 신청
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public Map<String, Object> InsertBreak(BreakDTO breakdto) throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();
        breakMapper.insertBreak(breakdto);
        rs.put("result", breakdto);
        return rs;
    }






}

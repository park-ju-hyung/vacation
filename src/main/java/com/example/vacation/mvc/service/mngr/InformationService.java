package com.example.vacation.mvc.service.mngr;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
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
public class InformationService {

    @Autowired
    @Qualifier("mngrPasswordEncoder")
    private PasswordEncoder mngrPasswordEncoder;


    private final InformationMapper informationMapper;



    // 상세보기
    @Transactional(readOnly = true)
    public EmployeeVO view(EmployeeDTO employeedto) throws Exception {
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);
        employeedto.setEmpNo(empNo);
        System.out.println("view empNo: " + empNo);
        return informationMapper.employeeVO(employeedto);
    }

    //수정
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public Map<String, Object> modify(EmployeeDTO employeedto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);
        employeedto.setEmpNo(empNo);
        employeedto.setEmpPassword(mngrPasswordEncoder.encode(employeedto.getEmpPassword()));
        System.out.println("modify empNo: " + empNo);
        System.out.println("modify empNo: " + employeedto.getEmpPassword());


        // 기본 정보 업데이트
        informationMapper.employeeUpdate(employeedto);

        rs.put("result", employeedto);
        return rs;
    }



}

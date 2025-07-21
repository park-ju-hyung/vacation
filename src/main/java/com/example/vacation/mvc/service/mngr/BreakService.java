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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    public Map<String, Object> InsertBreak(BreakFormDTO breakFormDTO) throws Exception {

        EmployeeDTO employeedto = breakFormDTO.getEmployee();
        System.out.println("employeedto:" + employeedto);
        BreakDTO breakData = breakFormDTO.getBreakData();

        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);
        employeedto.setEmpNo(empNo);
        System.out.println("empNo:" + empNo);
        employeedto.setEmpName(employeedto.getEmpName());
        employeedto.setEmpBirth(employeedto.getEmpBirth());
        employeedto.setPosition(employeedto.getPosition());
        employeedto.setDepartment(employeedto.getPosition());

        Map<String, Object> rs = new HashMap<String, Object>();
        breakMapper.insertBreak(breakFormDTO);
        rs.put("result", breakFormDTO);
        return rs;
    }






}

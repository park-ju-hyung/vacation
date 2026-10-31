package com.example.vacation.mvc.service.admin;

import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.mapper.admin.EmployeeMapper;
import com.example.vacation.mvc.mapper.admin.RetireMapper;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetireService {

    private final RetireMapper retireMapper;

    // 휴직 퇴직시 날짜, 사유 list
    @Transactional(readOnly = false)
    public Map<String, Object> RetireStatusList(EmployeeStatusDTO employeestatusdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        int pageNo = employeestatusdto.getPageNo() == 0 ? 1 : employeestatusdto.getPageNo();
        int pageSize = employeestatusdto.getPageSize() == 0 ? 10 : employeestatusdto.getPageSize();
        int pageBlock = employeestatusdto.getPageBlock() == 0 ? 10 : employeestatusdto.getPageBlock();
        employeestatusdto.setPageNo(pageNo);
        employeestatusdto.setPageSize(pageSize);
        employeestatusdto.setPageBlock(pageBlock);
        employeestatusdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<EmployeeStatusVO> list = retireMapper.RetireStatusList(employeestatusdto);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            employeestatusdto.setPageNo(pageNo);
            employeestatusdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = retireMapper.RetireStatusList(employeestatusdto);
        }

        int totalCount = retireMapper.RetireStatusCount(employeestatusdto);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("employeestatusDTO", employeestatusdto);
        rs.put("list", list);
        rs.put("totalCount", totalCount);
        rs.put("totalPageNo", totalPageNo);
        rs.put("pagingHTML", pagingHTML);

        return rs;
    }

    // 상세보기
    @Transactional(readOnly = true)
    public EmployeeStatusVO view(EmployeeStatusDTO employeestatusdto) throws Exception {
        return retireMapper.employeeStatusVO(employeestatusdto);
    }



}

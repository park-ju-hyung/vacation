package com.example.vacation.mvc.service.admin;

import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.mapper.admin.LoaMapper;
import com.example.vacation.mvc.mapper.admin.RetireMapper;
import com.example.vacation.mvc.vo.EmployeeStatusVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoaService {

    private final LoaMapper loaMapper;

    // 휴직 퇴직시 날짜, 사유 list
    @Transactional(readOnly = false)
    public Map<String, Object> LoaStatusList(EmployeeStatusDTO employeestatusdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        int pageNo = employeestatusdto.getPageNo() == 0 ? 1 : employeestatusdto.getPageNo();
        int pageSize = employeestatusdto.getPageSize() == 0 ? 10 : employeestatusdto.getPageSize();
        int pageBlock = employeestatusdto.getPageBlock() == 0 ? 10 : employeestatusdto.getPageBlock();
        employeestatusdto.setPageNo(pageNo);
        employeestatusdto.setPageSize(pageSize);
        employeestatusdto.setPageBlock(pageBlock);
        employeestatusdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<EmployeeStatusVO> list = loaMapper.LoaStatusList(employeestatusdto);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            employeestatusdto.setPageNo(pageNo);
            employeestatusdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = loaMapper.LoaStatusList(employeestatusdto);
        }

        int totalCount = loaMapper.LoaStatusCount(employeestatusdto);
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
        return loaMapper.employeeStatusVO(employeestatusdto);
    }



}

package com.example.vacation.mvc.service.mngr;

import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.mapper.EmployeeMapper;
import com.example.vacation.mvc.mapper.RetireMapper;
import com.example.vacation.mvc.vo.EmployeeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetireService {

    private final RetireMapper retireMapper;

    //list
    @Transactional(readOnly = false)
    public Map<String, Object> retirelist(EmployeeDTO employeeDTO) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        int pageNo = employeeDTO.getPageNo() == 0 ? 1 : employeeDTO.getPageNo();
        int pageSize = employeeDTO.getPageSize() == 0 ? 10 : employeeDTO.getPageSize();
        int pageBlock = employeeDTO.getPageBlock() == 0 ? 10 : employeeDTO.getPageBlock();
        employeeDTO.setPageNo(pageNo);
        employeeDTO.setPageSize(pageSize);
        employeeDTO.setPageBlock(pageBlock);
        employeeDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<EmployeeVO> list = retireMapper.retirelist(employeeDTO);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            employeeDTO.setPageNo(pageNo);
            employeeDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = retireMapper.retirelist(employeeDTO);
        }

        int totalCount = retireMapper.retireCount(employeeDTO);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("employeeDTO", employeeDTO);
        rs.put("list", list);
        rs.put("totalCount", totalCount);
        rs.put("totalPageNo", totalPageNo);
        rs.put("pagingHTML", pagingHTML);

        return rs;
    }

    // 상세보기
    @Transactional(readOnly = true)
    public EmployeeVO view(EmployeeDTO employeeDTO) throws Exception {
        return retireMapper.retireVO(employeeDTO);
    }


}

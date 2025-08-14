package com.example.vacation.mvc.service.site;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.BreakListDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.mapper.site.BreakListMapper;
import com.example.vacation.mvc.mapper.site.BreakMapper;
import com.example.vacation.mvc.vo.BreakListVO;
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
public class BreakListService {

    private final BreakListMapper breakListMapper;

    // list
    @Transactional(readOnly = false)
    public Map<String, Object> AllBreakList(BreakListDTO breakListDTO) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        // 로그인한 empNO 정보 가져오기
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);
        breakListDTO.setEmpNo(empNo);
        System.out.println("empNo0814: " + breakListDTO.getEmpNo());


        int pageNo = breakListDTO.getPageNo() == 0 ? 1 : breakListDTO.getPageNo();
        int pageSize = breakListDTO.getPageSize() == 0 ? 10 : breakListDTO.getPageSize();
        int pageBlock = breakListDTO.getPageBlock() == 0 ? 10 : breakListDTO.getPageBlock();
        breakListDTO.setPageNo(pageNo);
        breakListDTO.setPageSize(pageSize);
        breakListDTO.setPageBlock(pageBlock);
        breakListDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<BreakListVO> list = breakListMapper.AllBreakList(breakListDTO);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            breakListDTO.setPageNo(pageNo);
            breakListDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = breakListMapper.AllBreakList(breakListDTO);
        }

        int totalCount = breakListMapper.AllBreakCount(breakListDTO);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("BreakListDTO", breakListDTO);
        rs.put("list", list);
        rs.put("totalCount", totalCount);
        rs.put("totalPageNo", totalPageNo);
        rs.put("pagingHTML", pagingHTML);

        return rs;
    }







}

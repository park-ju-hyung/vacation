package com.example.vacation.mvc.service.mngr;

import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.mapper.ManageMapper;
import com.example.vacation.mvc.vo.BreakVO;
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
public class ManageService {

    private final ManageMapper manageMapper;

    //list
    @Transactional(readOnly = false)
    public Map<String, Object> manageList(BreakDTO breakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        int pageNo = breakdto.getPageNo() == 0 ? 1 : breakdto.getPageNo();
        int pageSize = breakdto.getPageSize() == 0 ? 10 : breakdto.getPageSize();
        int pageBlock = breakdto.getPageBlock() == 0 ? 10 : breakdto.getPageBlock();
        breakdto.setPageNo(pageNo);
        breakdto.setPageSize(pageSize);
        breakdto.setPageBlock(pageBlock);
        breakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<BreakVO> list = manageMapper.manageList(breakdto);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            breakdto.setPageNo(pageNo);
            breakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = manageMapper.manageList(breakdto);
        }

        int totalCount = manageMapper.manageCount(breakdto);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("breakDTO", breakdto);
        rs.put("list", list);
        rs.put("totalCount", totalCount);
        rs.put("totalPageNo", totalPageNo);
        rs.put("pagingHTML", pagingHTML);

        return rs;
    }

    // 신청자 정보 + 휴가 데이터
    @Transactional(readOnly = true)
    public BreakVO ManageView(BreakDTO breakdto) throws Exception {
        return manageMapper.breakVO(breakdto);
    }

    /**001:임시저장,002:제출,003:반려,004:승인**/
    // 승인 처리
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public Map<String, Object> updateStatus(BreakDTO breakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();
        if ("004".equals(breakdto.getStatus())){
            manageMapper.confirmBreak(breakdto);
        } else if("003".equals(breakdto.getStatus())){
            manageMapper.returnBreak(breakdto);
        } else if("002".equals(breakdto.getStatus())){
            manageMapper.requestBreak(breakdto);
        }

        rs.put("result", breakdto);
        return rs;
    }









}

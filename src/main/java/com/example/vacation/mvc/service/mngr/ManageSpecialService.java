package com.example.vacation.mvc.service.mngr;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.mapper.mngr.ManageSpecialMapper;
import com.example.vacation.mvc.vo.EmployeeVO;
import com.example.vacation.mvc.vo.SpecialBreakVO;
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
public class ManageSpecialService {

    private final ManageSpecialMapper manageSpecialMapper;

    //list
    @Transactional(readOnly = false)
    public Map<String, Object> SpecialBreakList(SpecialBreakDTO Specialbreakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        int pageNo = Specialbreakdto.getPageNo() == 0 ? 1 : Specialbreakdto.getPageNo();
        int pageSize = Specialbreakdto.getPageSize() == 0 ? 10 : Specialbreakdto.getPageSize();
        int pageBlock = Specialbreakdto.getPageBlock() == 0 ? 10 : Specialbreakdto.getPageBlock();
        Specialbreakdto.setPageNo(pageNo);
        Specialbreakdto.setPageSize(pageSize);
        Specialbreakdto.setPageBlock(pageBlock);
        Specialbreakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<SpecialBreakVO> list = manageSpecialMapper.SpecialBreakList(Specialbreakdto);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            Specialbreakdto.setPageNo(pageNo);
            Specialbreakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = manageSpecialMapper.SpecialBreakList(Specialbreakdto);
        }

        int totalCount = manageSpecialMapper.SpecialBreakCount(Specialbreakdto);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("SpecialBreakDTO", Specialbreakdto);
        rs.put("list", list);
        rs.put("totalCount", totalCount);
        rs.put("totalPageNo", totalPageNo);
        rs.put("pagingHTML", pagingHTML);

        return rs;
    }

    // 신청자 정보 + 휴가 데이터
    @Transactional(readOnly = true)
    public SpecialBreakVO SpecialBreakView(SpecialBreakDTO Specialbreakdto) throws Exception {
        return manageSpecialMapper.SpecialBreakVO(Specialbreakdto);
    }








}

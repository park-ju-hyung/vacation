package com.example.vacation.mvc.service.mngr;

import com.example.vacation.common.constant.SessionConstant;
import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.dto.SickBreakDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.mapper.mngr.ManageSickMapper;
import com.example.vacation.mvc.vo.SickBreakVO;
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
public class ManageSickService {

    private final ManageSickMapper manageSickMapper;

    //list
    @Transactional(readOnly = false)
    public Map<String, Object> SickBreakList(SickBreakDTO Sickbreakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        // 로그인한 empNO 정보 가져오기
        String empNo = (String) SessionManager.getSession().getAttribute(SessionConstant.SESSION_MANAGER_ID);
        Sickbreakdto.setEmpNo(empNo);


        int pageNo = Sickbreakdto.getPageNo() == 0 ? 1 : Sickbreakdto.getPageNo();
        int pageSize = Sickbreakdto.getPageSize() == 0 ? 10 : Sickbreakdto.getPageSize();
        int pageBlock = Sickbreakdto.getPageBlock() == 0 ? 10 : Sickbreakdto.getPageBlock();
        Sickbreakdto.setPageNo(pageNo);
        Sickbreakdto.setPageSize(pageSize);
        Sickbreakdto.setPageBlock(pageBlock);
        Sickbreakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<SickBreakVO> list = manageSickMapper.SickBreakList(Sickbreakdto);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            Sickbreakdto.setPageNo(pageNo);
            Sickbreakdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = manageSickMapper.SickBreakList(Sickbreakdto);
        }

        int totalCount = manageSickMapper.SickBreakCount(Sickbreakdto);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("SickBreakDTO", Sickbreakdto);
        rs.put("list", list);
        rs.put("totalCount", totalCount);
        rs.put("totalPageNo", totalPageNo);
        rs.put("pagingHTML", pagingHTML);

        return rs;
    }

    // 신청자 정보 + 휴가 데이터
    @Transactional(readOnly = true)
    public SickBreakVO SickBreakView(SickBreakDTO sickbreakdto) throws Exception {
        return manageSickMapper.SickBreakVO(sickbreakdto);
    }

    /**001:임시저장,002:제출,003:반려,004:승인**/
    // 승인 처리
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public Map<String, Object> updateStatus(SickBreakDTO Sickbreakdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();
        if ("004".equals(Sickbreakdto.getStatus())){
            manageSickMapper.confirmBreak(Sickbreakdto);
        } else if("003".equals(Sickbreakdto.getStatus())){
            manageSickMapper.returnBreak(Sickbreakdto);
        } else if("002".equals(Sickbreakdto.getStatus())){
            manageSickMapper.requestBreak(Sickbreakdto);
        }

        rs.put("result", Sickbreakdto);
        return rs;
    }








}

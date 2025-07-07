package com.example.vacation.mvc.service.mngr;

import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.mapper.EmployeeMapper;
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
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    //list
    @Transactional(readOnly = false)
    public Map<String, Object> Employeelist(EmployeeDTO employeeDTO) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        int pageNo = employeeDTO.getPageNo() == 0 ? 1 : employeeDTO.getPageNo();
        int pageSize = employeeDTO.getPageSize() == 0 ? 10 : employeeDTO.getPageSize();
        int pageBlock = employeeDTO.getPageBlock() == 0 ? 10 : employeeDTO.getPageBlock();
        employeeDTO.setPageNo(pageNo);
        employeeDTO.setPageSize(pageSize);
        employeeDTO.setPageBlock(pageBlock);
        employeeDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<EmployeeVO> list = employeeMapper.Employeelist(employeeDTO);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            employeeDTO.setPageNo(pageNo);
            employeeDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = employeeMapper.Employeelist(employeeDTO);
        }

        int totalCount = employeeMapper.EmployeeCount(employeeDTO);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("employeeDTO", employeeDTO);
        rs.put("list", list);
        rs.put("totalCount", totalCount);
        rs.put("totalPageNo", totalPageNo);
        rs.put("pagingHTML", pagingHTML);

        return rs;
    }

    // 등록
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public Map<String, Object> regist(EmployeeDTO employeeDTO) throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();

        //생년월일
        String birthDate = employeeDTO.getEmpBirth();
        birthDate = birthDate.replace("-", "");

        //난수만들기
        Random rand = new Random();
        int randomNum = rand.nextInt(9000) + 1000;

        //최종
        employeeDTO.setEmpNo(birthDate + randomNum);
        employeeDTO.setEmpPassword(birthDate + randomNum);
        System.out.println("empNo:" + birthDate + randomNum);
        System.out.println("Password:" + birthDate + randomNum);


        employeeMapper.insertEmployee(employeeDTO);
        rs.put("result", employeeDTO);
        return rs;
    }

    // 상세보기
    @Transactional(readOnly = true)
    public EmployeeVO view(EmployeeDTO employeeDTO) throws Exception {
        return employeeMapper.employeeVO(employeeDTO);
    }

    //수정
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public Map<String, Object> modify(EmployeeDTO employeeDTO) throws Exception {
        Map<String, Object> rs = new HashMap<>();
        employeeMapper.employeeUpdate(employeeDTO);
        rs.put("result", employeeDTO);
        return rs;
    }

}

package com.example.vacation.mvc.service.admin;

import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.mapper.admin.EmployeeMapper;
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
public class EmployeeService {

    @Autowired
    @Qualifier("mngrPasswordEncoder")
    private PasswordEncoder mngrPasswordEncoder;


    private final EmployeeMapper employeeMapper;

    // 등록
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public Map<String, Object> regist(EmployeeFormDTO employeeformdto) throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();

        // dto 세팅
        EmployeeDTO employeedto = employeeformdto.getEmployee();
        EmployeeStatusDTO statusdto = employeeformdto.getStatus();

        // 사번 번호 만들기
        // 1. 생년월일
        String birthDate = employeedto.getEmpBirth();
        birthDate = birthDate.replace("-", "");

        // 2. 난수만들기
        Random rand = new Random();
        int randomNum = rand.nextInt(9000) + 1000;
        String empNo = birthDate + randomNum;

        // 3. 합치기
        employeedto.setEmpNo(birthDate + randomNum);
        employeedto.setEmpPassword(mngrPasswordEncoder.encode(birthDate + randomNum));


        // 직급별 연차 부여
        if ("사원".equals(employeedto.getPosition())) {
            employeedto.setTotalDays(BigDecimal.valueOf(3));
        } else if ("주임".equals(employeedto.getPosition())) {
            employeedto.setTotalDays(BigDecimal.valueOf(5));
        } else {
            employeedto.setTotalDays(BigDecimal.valueOf(8));
        }

        statusdto.setEmpName(employeedto.getEmpName());
        statusdto.setEmpBirth(employeedto.getEmpBirth());
        statusdto.setEmpNo(empNo);
        statusdto.setStatus("입사");
        statusdto.setStatusDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        employeeMapper.insertEmployee(employeedto);
        employeeMapper.insertStatus(statusdto);

        rs.put("result", employeedto);
        return rs;
    }

    //list
    @Transactional(readOnly = false)
    public Map<String, Object> Employeelist(EmployeeDTO employeedto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        int pageNo = employeedto.getPageNo() == 0 ? 1 : employeedto.getPageNo();
        int pageSize = employeedto.getPageSize() == 0 ? 10 : employeedto.getPageSize();
        int pageBlock = employeedto.getPageBlock() == 0 ? 10 : employeedto.getPageBlock();
        employeedto.setPageNo(pageNo);
        employeedto.setPageSize(pageSize);
        employeedto.setPageBlock(pageBlock);
        employeedto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<EmployeeVO> list = employeeMapper.employeelist(employeedto);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            employeedto.setPageNo(pageNo);
            employeedto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = employeeMapper.employeelist(employeedto);
        }

        int totalCount = employeeMapper.employeeCount(employeedto);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("employeeDTO", employeedto);
        rs.put("list", list);
        rs.put("totalCount", totalCount);
        rs.put("totalPageNo", totalPageNo);
        rs.put("pagingHTML", pagingHTML);

        return rs;
    }

    // 상세보기
    @Transactional(readOnly = true)
    public EmployeeVO view(EmployeeDTO employeedto) throws Exception {
        return employeeMapper.employeeVO(employeedto);
    }

    //수정
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public Map<String, Object> modify(EmployeeFormDTO employeeformdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();


        EmployeeDTO employeedto = employeeformdto.getEmployee();
        EmployeeStatusDTO statusdto = employeeformdto.getStatus();

        // 기본 정보 업데이트
        employeeMapper.employeeUpdate(employeedto);

        String status = employeedto != null ? employeedto.getStatus() : null;


        // 2. 휴직 또는 퇴직일 경우 상태 테이블 처리
        if ("휴직".equals(status) || "퇴직".equals(status)) {
            if (statusdto == null) {
                statusdto = new EmployeeStatusDTO();
            }

            // 기본 매핑
            statusdto.setEmpNo(employeedto.getEmpNo());
            statusdto.setEmpName(employeedto.getEmpName());
            statusdto.setEmpBirth(employeedto.getEmpBirth());
            statusdto.setStatus(status);

            // 3. 상태 테이블에 해당 사원의 기존 데이터가 있는지 확인
            EmployeeStatusDTO existingStatus = employeeMapper.selectStatusByEmpNo(employeedto.getEmpNo());

            if (existingStatus == null) {
                // 기존 데이터가 전혀 없으므로 신규 등록 (INSERT)
                System.out.println("신규 상태 이력 등록 (INSERT)");
                employeeMapper.insertStatus(statusdto);
            } else {
                // 이미 등록된 상태 데이터가 있으므로 수정 (UPDATE)
                System.out.println("기존 상태 이력 수정 (UPDATE)");
                // 기존 PK(statusId)를 세팅해 해당 로우만 정확히 타겟팅하여 수정
                statusdto.setStatusId(existingStatus.getStatusId());
                employeeMapper.statusUpdate(statusdto);
            }
        }

        rs.put("result", employeedto);
        rs.put("status", statusdto);
        return rs;
    }


    // 휴직 퇴직시 날짜, 사유 list
    @Transactional(readOnly = false)
    public Map<String, Object> EmployeeStatusList(EmployeeStatusDTO employeestatusdto) throws Exception {
        Map<String, Object> rs = new HashMap<>();

        int pageNo = employeestatusdto.getPageNo() == 0 ? 1 : employeestatusdto.getPageNo();
        int pageSize = employeestatusdto.getPageSize() == 0 ? 10 : employeestatusdto.getPageSize();
        int pageBlock = employeestatusdto.getPageBlock() == 0 ? 10 : employeestatusdto.getPageBlock();
        employeestatusdto.setPageNo(pageNo);
        employeestatusdto.setPageSize(pageSize);
        employeestatusdto.setPageBlock(pageBlock);
        employeestatusdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

        List<EmployeeStatusVO> list = employeeMapper.employeeStatuslist(employeestatusdto);

        if (pageNo != 1 && list.size() == 0) {
            pageNo = 1;
            employeestatusdto.setPageNo(pageNo);
            employeestatusdto.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
            list = employeeMapper.employeeStatuslist(employeestatusdto);
        }

        int totalCount = employeeMapper.employeeStatusCount(employeestatusdto);
        int totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
        String pagingHTML = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);

        rs.put("employeestatusDTO", employeestatusdto);
        rs.put("list", list);
        rs.put("totalCount", totalCount);
        rs.put("totalPageNo", totalPageNo);
        rs.put("pagingHTML", pagingHTML);


        //rs.put("employeeStatusVO", list.get(0)); // 이름과 타입 일치


        return rs;
    }

    /**계정 조회**/
    public EmployeeVO loadUserByUsername(String empNo) throws UsernameNotFoundException {
        EmployeeDTO mngr = new EmployeeDTO();
        mngr.setEmpNo(empNo);

        return employeeMapper.loadUserByUsername(mngr);
    }



}

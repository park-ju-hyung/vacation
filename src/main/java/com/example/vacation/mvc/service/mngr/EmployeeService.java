package com.example.vacation.mvc.service.mngr;

import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    // 등록
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public Map<String, Object> regist(EmployeeDTO employeeMapperDTO) throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();

        employeeMapper.insertEmployee(employeeMapperDTO);

        rs.put("result", employeeMapperDTO);
        return rs;
    }

}

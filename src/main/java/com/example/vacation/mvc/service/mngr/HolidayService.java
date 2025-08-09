package com.example.vacation.mvc.service.mngr;

import com.example.vacation.common.util.AppPagingUtil;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeFormDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.dto.HolidayDTO;
import com.example.vacation.mvc.mapper.admin.EmployeeMapper;
import com.example.vacation.mvc.mapper.HolidayMapper;
import com.example.vacation.mvc.vo.EmployeeStatusVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import com.example.vacation.mvc.vo.HolidayVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class HolidayService {
    private final HolidayMapper holidayMapper;
    @Transactional(readOnly = true)
    public Map<String, Object> selectHolidayList(HolidayDTO holidaydto) throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();

        List<HolidayVO> list = holidayMapper.selectHolidayList(holidaydto);
        rs.put("holidayDTO", holidaydto);
        rs.put("list", list);

        return rs;
    }
}

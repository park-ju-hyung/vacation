package com.example.vacation.mvc.mapper;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.HolidayDTO;
import com.example.vacation.mvc.vo.BreakVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import com.example.vacation.mvc.vo.HolidayVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface HolidayMapper {

	// holiday 조회 결과 리스트
	public List<HolidayVO> selectHolidayList(HolidayDTO holidaydto) throws Exception;

	// holiday 조회
	public HolidayVO selectHolidayInfo(HolidayDTO holidaydto) throws Exception;

}

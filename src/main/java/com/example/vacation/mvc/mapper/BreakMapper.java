package com.example.vacation.mvc.mapper;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.BreakFormDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.vo.BreakVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface BreakMapper {

	// 휴가 신청
	public void insertBreak(BreakDTO breakdto) throws Exception;

	// 신청자 정보 가져오기
	public EmployeeVO employeeVO(EmployeeDTO employeedto) throws Exception;

	//list
	public List<BreakVO> breaklist(BreakDTO breakdto) throws Exception;

	public int breakCount(BreakDTO breakdto) throws Exception;

	// 신청자 정보 + 휴가 데이터 가져오기
	public BreakVO breakVO(BreakDTO breakdto) throws Exception;

}

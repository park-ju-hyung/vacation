package com.example.vacation.mvc.mapper;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.vo.BreakVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import com.example.vacation.mvc.vo.SpecialBreakVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@DBMapper
@Repository
public interface SpecialBreakMapper {

	// 휴가 신청
	public void insertSpecialBreak(SpecialBreakDTO Specialbreakdto) throws Exception;

	// 신청자 정보 가져오기
	public EmployeeVO employeeVO(EmployeeDTO employeedto) throws Exception;

	//list
	public List<SpecialBreakVO> SpecialBreakList(SpecialBreakDTO Specialbreakdto) throws Exception;

	public int SpecialBreakCount(SpecialBreakDTO Specialbreakdto) throws Exception;

	// 신청자 정보 + 휴가 데이터 가져오기
	public SpecialBreakVO SpecialBreakVO(SpecialBreakDTO Specialbreakdto) throws Exception;

	// 휴가 삭제
	public int SpecialBreakDelete(SpecialBreakDTO Specialbreakdto) throws Exception;





}

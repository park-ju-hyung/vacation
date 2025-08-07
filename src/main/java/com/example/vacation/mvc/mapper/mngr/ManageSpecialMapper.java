package com.example.vacation.mvc.mapper.mngr;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.vo.EmployeeVO;
import com.example.vacation.mvc.vo.SpecialBreakVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface ManageSpecialMapper {

	// 신청자 정보 가져오기
	public EmployeeVO employeeVO(EmployeeDTO employeedto) throws Exception;

	//list
	public List<SpecialBreakVO> SpecialBreakList(SpecialBreakDTO Specialbreakdto) throws Exception;

	public int SpecialBreakCount(SpecialBreakDTO Specialbreakdto) throws Exception;

	// 신청자 정보 + 휴가 데이터 가져오기
	public SpecialBreakVO SpecialBreakVO(SpecialBreakDTO Specialbreakdto) throws Exception;

}

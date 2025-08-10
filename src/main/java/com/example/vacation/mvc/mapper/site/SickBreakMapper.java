package com.example.vacation.mvc.mapper.site;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.SickBreakDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.vo.BreakVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import com.example.vacation.mvc.vo.SickBreakVO;
import com.example.vacation.mvc.vo.SpecialBreakVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface SickBreakMapper {
	// 휴가 신청
	public void insertSickBreak(SickBreakDTO sickbreakdto) throws Exception;

	// 신청자 정보 가져오기
	public EmployeeVO employeeVO(EmployeeDTO employeedto) throws Exception;

	//list
	public List<SickBreakVO> SickBreakList(SickBreakDTO Sickbreakdto) throws Exception;

	public int SickBreakCount(SickBreakDTO Sickbreakdto) throws Exception;

	// 신청자 정보 + 휴가 데이터 가져오기
	public SickBreakVO SickBreakVO(SickBreakDTO sickbreakdto) throws Exception;

	// 휴가 삭제
	public int SickBreakDelete(SickBreakDTO sickbreakdto) throws Exception;




}

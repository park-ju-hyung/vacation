package com.example.vacation.mvc.mapper.mngr;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.vo.BreakVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@DBMapper
@Repository
public interface ManageMapper {

	//list
	public List<BreakVO> manageList(BreakDTO breakdto) throws Exception;

	public int manageCount(BreakDTO breakdto) throws Exception;

	// 신청자 정보 + 휴가 데이터 가져오기
	public BreakVO breakVO(BreakDTO breakdto) throws Exception;

	/**001:임시저장,002:제출,003:반려,004:승인**/
	// 제출 처리 002
	public int requestBreak(BreakDTO breakdto);

	// 반려 처리 003
	public int returnBreak(BreakDTO breakdto);

	// 승인 처리 004
	public int confirmBreak(BreakDTO breakdto);




}

package com.example.vacation.mvc.mapper.mngr;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.SickBreakDTO;
import com.example.vacation.mvc.dto.SpecialBreakDTO;
import com.example.vacation.mvc.vo.EmployeeVO;
import com.example.vacation.mvc.vo.SickBreakVO;
import com.example.vacation.mvc.vo.SpecialBreakVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface ManageSickMapper {

	// 신청자 정보 가져오기
	public EmployeeVO employeeVO(EmployeeDTO employeedto) throws Exception;

	//list
	public List<SickBreakVO> SickBreakList(SickBreakDTO Sickbreakdto) throws Exception;

	public int SickBreakCount(SickBreakDTO Sickbreakdto) throws Exception;

	// 신청자 정보 + 휴가 데이터 가져오기
	public SickBreakVO SickBreakVO(SickBreakDTO Sickbreakdto) throws Exception;

	/**001:임시저장,002:제출,003:반려,004:승인**/
	// 제출 처리 002
	public int requestBreak(SickBreakDTO Sickbreakdto);

	// 반려 처리 003
	public int returnBreak(SickBreakDTO Sickbreakdto);

	// 승인 처리 004
	public int confirmBreak(SickBreakDTO Sickbreakdto);

}

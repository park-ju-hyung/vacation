package com.example.vacation.mvc.mapper.admin;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.vo.EmployeeStatusVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface LoaMapper {

	// 퇴직 휴직 사유 list
	public List<EmployeeStatusVO> LoaStatusList(EmployeeStatusDTO employeestatusdto) throws Exception;

	public int LoaStatusCount(EmployeeStatusDTO employeestatusdto) throws Exception;

	// 상세보기
	public EmployeeStatusVO employeeStatusVO(EmployeeStatusDTO employeestatusdto) throws Exception;
}

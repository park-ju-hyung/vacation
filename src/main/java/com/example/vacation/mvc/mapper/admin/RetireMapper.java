package com.example.vacation.mvc.mapper.admin;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.vo.EmployeeStatusVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface RetireMapper {

	// 퇴직 휴직 사유 list
	public List<EmployeeStatusVO> RetireStatusList(EmployeeStatusDTO employeestatusdto) throws Exception;

	public int RetireStatusCount(EmployeeStatusDTO employeestatusdto) throws Exception;

	// 상세보기
	public EmployeeStatusVO employeeStatusVO(EmployeeStatusDTO employeestatusdto) throws Exception;
}

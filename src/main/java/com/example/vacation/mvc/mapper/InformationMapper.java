package com.example.vacation.mvc.mapper;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import com.example.vacation.mvc.vo.EmployeeStatusVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface InformationMapper {
	// 상세보기
	public EmployeeVO employeeVO(EmployeeDTO employeedto) throws Exception;

	// 수정
	public int employeeUpdate(EmployeeDTO employeedto) throws Exception;

}

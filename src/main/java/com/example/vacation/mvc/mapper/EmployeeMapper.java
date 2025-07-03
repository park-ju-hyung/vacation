package com.example.vacation.mvc.mapper;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.vo.EmployeeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface EmployeeMapper {
	//list
	public List<EmployeeVO> Employeelist(EmployeeDTO EmployeeDTO) throws Exception;

	public int EmployeeCount(EmployeeDTO EmployeeDTO) throws Exception;

	// 등록
	public void insertEmployee(EmployeeDTO employeedto) throws Exception;
}

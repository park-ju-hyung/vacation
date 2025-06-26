package com.example.vacation.mvc.mapper;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.EmployeeDTO;
import org.springframework.stereotype.Repository;

@DBMapper
@Repository
public interface EmployeeMapper {
	// 수요자 계정 생성
	public void insertEmployee(EmployeeDTO employeedto) throws Exception;
}

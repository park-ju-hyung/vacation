package com.example.vacation.mvc.mapper;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.vo.EmployeeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@DBMapper
@Repository
public interface RetireMapper {

	//list
	public List<EmployeeVO> retirelist(EmployeeDTO EmployeeDTO) throws Exception;

	public int retireCount(EmployeeDTO EmployeeDTO) throws Exception;

	// 상세보기
	public EmployeeVO retireVO(EmployeeDTO employeedto) throws Exception;


}

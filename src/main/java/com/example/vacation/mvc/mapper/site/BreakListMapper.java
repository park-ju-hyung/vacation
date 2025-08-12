package com.example.vacation.mvc.mapper.site;

import com.example.vacation.common.config.DBMapper;
import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.BreakListDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.vo.BreakListVO;
import com.example.vacation.mvc.vo.BreakVO;
import com.example.vacation.mvc.vo.EmployeeVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@DBMapper
@Repository
public interface BreakListMapper {

	// 신청자 정보 가져오기
	public EmployeeVO employeeVO(EmployeeDTO employeedto) throws Exception;

	//list
	public List<BreakListVO> AllBreakList(BreakListDTO breakListDTO) throws Exception;

	public int AllBreakCount(BreakListDTO breakListDTO) throws Exception;



}

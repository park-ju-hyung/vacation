package com.example.vacation.mvc.mapper;

import com.example.vacation.mvc.dto.UserDTO;
import org.springframework.stereotype.Repository;
import com.example.vacation.common.config.DBMapper;


import java.util.List;

@DBMapper
@Repository
public interface UserMapper {
	// 수요자 계정 생성
	public void insertUser(UserDTO dto) throws Exception;
}

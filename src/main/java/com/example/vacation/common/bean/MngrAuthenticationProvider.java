package com.example.vacation.common.bean;

import com.example.vacation.mvc.service.mngr.EmployeeService;
import com.example.vacation.mvc.vo.EmployeeVO;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class MngrAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	@Qualifier("mngrPasswordEncoder")
	PasswordEncoder mngrPasswordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// 폼 입력값 가져오기
		String id = (String) authentication.getPrincipal();
		String pwd = (String) authentication.getCredentials();
		
		EmployeeVO employeeVo = employeeService.loadUserByUsername(id);
		
		if(employeeVo == null)
			throw new BadCredentialsException("NullPointerException : not exist manager");
		System.out.println("employeeVo: " + employeeVo);
		System.out.println("입력받은 비밀번호: " + pwd);
		System.out.println("DB 비밀번호: " + employeeVo.getEmpPassword());
		if(!mngrPasswordEncoder.matches(pwd, employeeVo.getEmpPassword()))
			throw new BadCredentialsException("BadCredentialsException : a discrepancy between id and pwd");


        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("MANAGER"));
        return new UsernamePasswordAuthenticationToken(employeeVo, pwd, authorities);
        
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

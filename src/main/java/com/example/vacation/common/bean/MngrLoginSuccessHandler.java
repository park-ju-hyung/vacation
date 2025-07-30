package com.example.vacation.common.bean;

import com.example.vacation.common.util.SessionManager;
import com.example.vacation.mvc.service.mngr.EmployeeService;
import com.example.vacation.mvc.vo.EmployeeVO;
import groovy.util.logging.Slf4j;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
public class MngrLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {

		// 현재 세션 가져오기
		HttpSession session = request.getSession();

		// 세션 검사
		if(session != null) {
			// 기존의 값들을 지운다.
			SessionManager.removeSessionMngrInfo(session);
		}
		
		String mngrId = ((EmployeeVO)authentication.getPrincipal()).getEmpNo();
		EmployeeVO mngr = employeeService.loadUserByUsername(mngrId);
		
		try {
			SessionManager.setSessionMngrInfo(session, mngr);
		} catch (Exception e) {
			System.out.println("MngrLoginSuccessHandler : " + e.getMessage());
		}

		String role = mngr.getRole();

		// 리다이렉트
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		// 로그인 이후 가야할 목적지 지정
		if ("mngr".equalsIgnoreCase(role)) {
			System.out.println("role: " + role);
			String targetUrl = "/mngr/manage/list";
			redirectStrategy.sendRedirect(request, response, targetUrl);
		} else if ("user".equalsIgnoreCase(role)) {
			System.out.println("role: " + role);
			String targetUrl = "/site/informodify/modify";
			redirectStrategy.sendRedirect(request, response, targetUrl);
		} else if ("super".equalsIgnoreCase(role)) {
			System.out.println("role: " + role);
			String targetUrl = "/admin/Employee/list";
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}

		

	}

}

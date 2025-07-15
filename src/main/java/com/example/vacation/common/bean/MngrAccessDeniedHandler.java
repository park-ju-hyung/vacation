package com.example.vacation.common.bean;

import com.example.vacation.common.util.SessionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

public class MngrAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
					   AccessDeniedException accessDeniedException) throws IOException, ServletException {

		// 현재 세션 가져오기
		HttpSession session = request.getSession();

		// 세션 검사
		if(session != null) {
			// 기존의 값들을 지운다.
			SessionManager.removeSessionMngrInfo(session);
		}
		
		// 리다이렉트
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		// 권한이 없는 사용자의 접근이 있을때 사용자가 가야할 경로 지정
		String targetUrl = "/mngr/loginPage";
		// 해당 목적지를 바탕으로 요청과 응답 다시 설정 
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null) {
			targetUrl = savedRequest.getRedirectUrl();
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
		
	}

}

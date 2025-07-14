package com.example.vacation.common.interceptor;

import com.example.vacation.common.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
public class MngrInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			if(authentication.getPrincipal() instanceof User) {
				User user = User.class.cast(authentication.getPrincipal());

				// 스프링 시큐리티에는 로그인 정보가 있는데 HTTP 세션에 로그인 관련 정보가 없는 경우에는 로그아웃 처리
				if(session.getAttribute(SessionConstant.MNGR) == null) {
					log.info("session timeout: {}", user.getUsername());
					response.sendRedirect("/mngr/logout");
				}
			}
		}

		

	}
	

}


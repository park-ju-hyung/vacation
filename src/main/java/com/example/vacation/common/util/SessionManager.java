package com.example.vacation.common.util;

import com.example.vacation.common.constant.SessionConstant;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.example.vacation.mvc.vo.EmployeeVO;


public class SessionManager {

	public SessionManager() {
		// TODO Auto-generated constructor stub
	}

	public static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true);		
	}

	public static void setSessionMngrInfo(HttpSession session, EmployeeVO vo) throws Exception {
		if (null == vo) {
			throw new Exception("Not defined login ID.");
		}
		session.setAttribute(SessionConstant.SESSION_MANAGER_NAME, vo.getEmpName()); // 이름
		session.setAttribute(SessionConstant.SESSION_MANAGER_ID, vo.getEmpNo()); // 사번 = 아이디
	}
	
	public static void removeSessionMngrInfo(HttpSession session) {
		session.removeAttribute(SessionConstant.SESSION_MANAGER_NAME);
		session.removeAttribute(SessionConstant.SESSION_MANAGER_ID);
	}
	
}

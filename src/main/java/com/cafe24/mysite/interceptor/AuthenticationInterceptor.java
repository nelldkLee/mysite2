package com.cafe24.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Authentication;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("handler : " + handler);
		if( handler instanceof HandlerMethod == false) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		Authentication authenticationClass = handlerMethod.getBean().getClass().getAnnotation(Authentication.class);
		Authentication authenticationMethod = handlerMethod.getMethodAnnotation(Authentication.class);
		
		if(authenticationClass == null && authenticationMethod == null) {
			return true;
		}
		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");
		
		if(authUser != null) {
			return true;
		}
		response.sendRedirect(request.getContextPath() + "/user/login");
		return false;
	}
}

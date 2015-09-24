package com.yanghui.auth.biz.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yanghui.auth.biz.model.User;

public class AppContext {
	
	public static User getCurrentAppUser(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return (User)request.getSession().getAttribute("user");
	}
}

package com.erp.utils;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
	
	public static void pirntMessage(Map<String,String> map){
		
		
		
	}
	
	public static void clearSession(HttpServletRequest request) {
		Enumeration<String> enumeration =  request.getSession().getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			request.getSession().removeAttribute(key);
		}
	}
	
}

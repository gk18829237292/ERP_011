package com.erp.utils;

import java.io.UnsupportedEncodingException;

public class StringUtils {
	
	private final static String ISO = "ISO8859-1";
	private final static String UTF_8 = "utf-8";
	
	public static String change2Utf8(String str) {
		if(str == null) return "";
		try {
			str = new String(str.getBytes(ISO),UTF_8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
}

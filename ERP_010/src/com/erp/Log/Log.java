package com.erp.Log;

public class Log {
	
	public static void logInfo(String title,String msg){
		System.out.println(title + "\n\t" + msg);
	}
	
	public static void logError(String title,String msg){
		System.err.println(title + "\n\t" + msg);
	}
}

package com.erp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.erp.Log.Log;

public class TimeUtils {
	
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static long dayTimes = 24 * 60 * 60 * 1000;
	
	public static long getNowLongTime() {
		return new Date().getTime();
	}
	
	public static String getNowTime(){
		return convert2String(new Date().getTime());
	}
	
	public static String convert2String(String time){
		return convert2String(Long.parseLong(time));
	}
	
	public static String convert2String(long time){
		return sFormat.format(new Date(time));
	}
	
	public static long convert2Long(String time){
		long ans = 0;
		try {
			ans = sFormat.parse(time).getTime();
		} catch (ParseException e) {
		}
		return ans;
	}
	
	public static int getDays(long time){
		long days = time / dayTimes;
		return (int)days;
	}
	
}

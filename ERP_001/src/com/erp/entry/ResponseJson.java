package com.erp.entry;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseJson {
	
	public static JSONObject getErrorJson(){
		JSONObject json = new JSONObject();
		try {
			json.put("error", "");
		} catch (JSONException e) {
			return null;
		}
		return json;
	}
	
	public static JSONObject getCurrentJson(){
		JSONObject json = new JSONObject();
		try {
			json.put("noerror", "");
		} catch (JSONException e) {
			return null;
		}
		return json;
	}
	
}

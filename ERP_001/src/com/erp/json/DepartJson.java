package com.erp.json;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erp.entry.DepartmentEntry;

public class DepartJson {

	public static JSONArray convert(List<DepartmentEntry> entries) throws JSONException{
		JSONArray jsons = new JSONArray();
		for(DepartmentEntry entry:entries){
			jsons.put(convert(entry));
		}
		return jsons;
	}
	
	public static JSONObject convert(DepartmentEntry entry) throws JSONException{
		
		JSONObject json = new JSONObject();
		json.put("departId", entry.getDepartmentId());
		json.put("departName", entry.getDepartmentName());
		return json;
	}
	

	
}

package com.erp.json;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erp.entry.ReportEntry;

public class ReportJson {
	
	
	public static JSONObject convert(ReportEntry entry) throws JSONException{
		
		JSONObject json = new JSONObject();
		json.put("account", entry.getAccount());
		json.put("name", entry.getName());
		json.put("id", entry.getReportId());
		json.put("comment", entry.getComment());
		json.put("index", entry.getReportIndex());
		json.put("time", entry.getTime());
		
		if(entry.getPicture() != null && entry.getPicture().size() != 0){
			JSONArray jsonArray  = new JSONArray();
			for(String path:entry.getPicture()){
				jsonArray.put(path);
			}
			json.put("pics", jsonArray);
		}
		if(entry.isCheckd()){
			JSONArray jsonArray  = SuperviseJson.convert(entry.getSupervise());
			json.put("supervise", jsonArray);
		}
		
		return json;
	}
	
	public static JSONArray convert(List<ReportEntry> enties) throws JSONException{
		JSONArray jsons = new JSONArray();
		for(ReportEntry entry:enties){
			jsons.put(convert(entry));
		}
		return jsons;
	}
	
}

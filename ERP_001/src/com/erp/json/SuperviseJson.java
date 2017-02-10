package com.erp.json;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erp.entry.SuperviseEntry;

public class SuperviseJson {
	
	public static JSONObject convert(SuperviseEntry entry) throws JSONException{
		JSONObject json = new JSONObject();
		json.put("account", entry.getAccount());
		json.put("name", entry.getName());
		json.put("id", entry.getSupervise_id());
		json.put("comment", entry.getComment());
		json.put("time", entry.getTime());
		if(entry.getPicture() != null && entry.getPicture().size() != 0){
			JSONArray jsonArray  = new JSONArray();
			for(String path:entry.getPicture()){
				jsonArray.put(path);
			}
			json.put("pics", jsonArray);
		}
		return json;
	}
	
	public static JSONArray convert(List<SuperviseEntry> entries) throws JSONException{
		JSONArray jsons = new JSONArray();
		for(SuperviseEntry entry:entries){
			jsons.put(convert(entry));
		}
		return jsons;
	}
	
}

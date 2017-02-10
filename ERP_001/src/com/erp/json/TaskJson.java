package com.erp.json;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erp.entry.AssignmentEntry;
import com.erp.entry.DepartmentEntry;

public class TaskJson {
	
	public static JSONArray convert(List<AssignmentEntry> entries) throws JSONException{
		JSONArray jsons = new JSONArray();
		for(AssignmentEntry entry:entries){
			jsons.put(convert(entry));
		}
		return jsons;
	}
	
	public static JSONObject convert(AssignmentEntry entry) throws JSONException{
		
		JSONObject json = new JSONObject();
		System.out.println(entry);
		json.put("taskId", entry.getAssignment_id());
		json.put("departmentId", entry.getDepartmentId());
		json.put("startTime", entry.getTime_1());
		json.put("endTime", entry.getTime_2());
		json.put("createAccount", entry.getCreateAccount());
		json.put("place", entry.getPlace());
		json.put("financing", entry.getFinancing());
		json.put("goal", entry.getGoal());
		json.put("type", entry.getType());
		json.put("num", entry.getNum());
		json.put("completNum", entry.getCompletNum());
		json.put("taskName", entry.getAssignmentName());
		json.put("departName", entry.getDepartmentName());
		return json;
	}
	

}

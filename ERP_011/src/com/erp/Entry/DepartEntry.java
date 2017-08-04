package com.erp.Entry;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DepartEntry {
	private String departId;
	private String departName;
	private List<TaskEntry> tasks = new ArrayList<>();
	
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	
	public List<TaskEntry> getTasks() {
		return tasks;
	}
	
	public DepartEntry() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "DepartEntry [departId=" + departId + ", departName=" + departName + ", tasks=" + tasks + "]\n";
	}
	
	public JSONObject write2Json() throws JSONException {
		JSONObject json = new JSONObject();
		/**
		 * 	private String departId;
	private String departName;
	private List<TaskEntry> tasks = new ArrayList<>();
		 */
		json.put("id", departId);
		json.put("name", departName);
		JSONArray jsonArray  = new JSONArray();
		for(TaskEntry entry:tasks){
			jsonArray.put(entry.write2Json());
		}
		json.put("array", jsonArray);
		return json;
	}


	
	
}

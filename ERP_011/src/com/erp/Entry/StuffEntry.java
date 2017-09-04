package com.erp.Entry;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erp.utils.StringUtils;

public class StuffEntry {
	
	public static final String EMPTY_STR = "————";
	
	private String account;
	private String pwd;
	private DepartEntry depart;
	private String type;
	private String name;
	private String telNum;
	private int isLeader;
	public StuffEntry(){
		
	}

	public int getIsLeader() {
		return isLeader;
	}
	
	public boolean isType0_1(){
		return !type.equals("2");
	}
	
	public boolean isType0() {
		return type.equals("0");
	}
	
	public boolean isType2_leader() {
		return type.equals("2") && isLeader == 1;
	}

	public void setIsLeader(int isLeader) {
		this.isLeader = isLeader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public DepartEntry getDepart() {
		return depart;
	}


	public void setDepart(DepartEntry depart) {
		this.depart = depart;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "StuffEntry [account=" + account + ", pwd=" + pwd + ", depart=" + depart + ", type=" + type + ", name="
				+ name + ", telNum=" + telNum + ", isLeader=" + isLeader + "]";
	}



	
	public void makeItFull(){
		if(StringUtils.isSpace(pwd)) pwd = EMPTY_STR;
		if(StringUtils.isSpace(name)) name = EMPTY_STR;
		if(StringUtils.isSpace(telNum)) telNum = EMPTY_STR;
	}
 
	public JSONObject write2Json() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		/**
		 * 	private String account;
	private String pwd;
	private DepartEntry depart;
	private String type;
	private String name;
	private String telNum;
	private int isLeader;
		 */
		
		jsonObject.put("account", account);
		jsonObject.put("type",isType2_leader()?"3":type);
		jsonObject.put("name", name);
		jsonObject.put("telNum", telNum);
		jsonObject.put("password", pwd);
		if(depart != null){
			jsonObject.put("departId",depart.getDepartId());
			jsonObject.put("departName", depart.getDepartName());
		}else{
			jsonObject.put("departId", "");
			jsonObject.put("departName", "");
		}
		
		return jsonObject;
	}
	
	public static JSONObject convertList2Json(List<StuffEntry> stuffEntries) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		for(StuffEntry entry:stuffEntries){
			array.put(entry.write2Json());
		}
		jsonObject.put("users", array);
		return jsonObject;
	}
	
	
	
	
}

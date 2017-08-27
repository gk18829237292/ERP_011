package com.erp.Entry;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
create table Advice(
	addvise_id bigint primary key auto_increment,
    time long not null,
    adviseIndex int not null,
    comment nvarchar(1000) not null,
    picture nvarchar(1000) not null,
    task_id bigint not null,
    type int not null,
    foreign key(task_id) references Task(task_id) on delete cascade
);
 * @author ke.gao
 *
 */
public class AdviceEntry {
	
	private String adviceId;
	private String time;
	private int adviceIndex;
	private String comment;
	private List<String> picture;
	private String taskId;
	private int type;
	private String name;
	private int star;
	
	
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdviceId() {
		return adviceId;
	}
	public void setAdviceId(String adviceId) {
		this.adviceId = adviceId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getAdviceIndex() {
		return adviceIndex;
	}
	public void setAdviceIndex(int adviceIndex) {
		this.adviceIndex = adviceIndex;
	}
	public String getComment() {
		return comment;
	}
	public void setCommment(String comment) {
		this.comment = comment;
	}
	public List<String> getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = Arrays.asList(picture.split(";"));
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "AdviceEntry [adviceId=" + adviceId + ", time=" + time + ", adviceIndex=" + adviceIndex + ", comment="
				+ comment + ", picture=" + picture + ", taskId=" + taskId + ", type=" + type + "]";
	}
	
	public JSONObject write2Json() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", adviceId);
		json.put("adviceIndex", adviceIndex);
		json.put("comment", comment);
		json.put("time", time);
		JSONArray jsonArray = new JSONArray();
		if(picture != null){
			for(String str:picture){
				jsonArray.put(str);
			}
		}
		json.put("pics", jsonArray);
		json.put("taskId", taskId);
		json.put("type", type);
		json.put("star", star);
		json.put("name", name);
		return json;
	}
	
}

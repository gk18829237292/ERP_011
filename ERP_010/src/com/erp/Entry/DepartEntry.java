package com.erp.Entry;

import java.util.ArrayList;
import java.util.List;


public class DepartEntry {
	private String departId;
	private String departName;
	private DepartClassEntry departClass;
	private List<TaskEntry> tasks = new ArrayList<>();
	
	public DepartClassEntry getDepartClass() {
		return departClass;
	}
	public void setDepartClass(DepartClassEntry departClass) {
		this.departClass = departClass;
	}
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
	
	


	
	
}

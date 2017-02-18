package com.erp.Entry;

import java.util.ArrayList;
import java.util.List;

public class DepartClassEntry {
	
	private String departClassId;
	private String departClassName;
	private List<DepartEntry> departs = new ArrayList<>();
	
	public List<DepartEntry> getDeparts() {
		return departs;
	}
	
	public DepartClassEntry() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDepartClassName() {
		return departClassName;
	}
	public void setDepartClassName(String departClassName) {
		this.departClassName = departClassName;
	}

	public String getDepartClassId() {
		return departClassId;
	}
	public void setDepartClassId(String departClassId) {
		this.departClassId = departClassId;
	}
	@Override
	public String toString() {
		return "DepartClassEntry [departClassId=" + departClassId + ", departClassName=" + departClassName
				+ ", departs=" + departs + "]\n";
	}
	
	
	
	
	
	
	
}

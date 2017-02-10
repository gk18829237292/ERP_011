package com.erp.entry;

public class DepartmentEntry {
	private String departmentId;
	private String departmentName;
	private int type;

	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "DepartmentEntry [departmentId=" + departmentId + ", departmentName=" + departmentName + ", type=" + type
				+ "]";
	}
	
	
	
}

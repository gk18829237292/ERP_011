package com.erp.entry;

import com.erp.Dao.DepartmentDao;
import com.erp.Dao.Stuff_DepartmentDao;

public class StuffEntry {
	
	private String account;
	private String name;
	private String pwd;
	private int type;
	
	private DepartmentEntry department = null;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "StuffEntry [account=" + account + ", name=" + name + ", type=" + type + "]";
	}
	
	public DepartmentEntry getDepartment() {
		if(department == null){
			System.out.println(account);
			long id = Stuff_DepartmentDao.queryDepartmentId(account);
//			System.out.println();
			department = DepartmentDao.getDepartment(id+"");
			setDepartment(department);
		}
		return department;
	}
	public void setDepartment(DepartmentEntry department) {
		this.department = department;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
	
	 
	
}

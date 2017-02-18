package com.erp.Entry;

public class StuffEntry_2 {
	
	private String account;
	private String pwd;
	private DepartEntry depart;
	private String type;
	private String name;
	private String telNum;
	public StuffEntry_2(){
		
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
				+ name + ", telNum=" + telNum + "]";
	}


 

	
	
	
	
}

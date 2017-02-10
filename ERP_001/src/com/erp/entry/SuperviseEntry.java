package com.erp.entry;

import java.util.Arrays;
import java.util.List;

import com.erp.Dao.StuffDao;
import com.erp.utils.TimeUtils;

public class SuperviseEntry {
	
	String supervise_id;
	String report_id;
	String account;
	String comment;
	List<String> picture;
	long superviseTime;
	public String getSupervise_id() {
		return supervise_id;
	}
	public void setSupervise_id(String supervise_id) {
		this.supervise_id = supervise_id;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<String> getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = Arrays.asList(picture.split(";"));
//		this.picture = picture;
	}
	public String getSuperviseTime() {
		return TimeUtils.convert2String(superviseTime);
	}
	public void setSuperviseTime(long superviseTime) {
		this.superviseTime = superviseTime;
	}
	@Override
	public String toString() {
		return "SuperviseEntry [supervise_id=" + supervise_id + ", report_id=" + report_id + ", account=" + account
				+ ", comment=" + comment + ", picture=" + picture + ", superviseTime=" + superviseTime + "]";
	}
	
	private String name;
	
	public String getName() {
		if (name == null) {
			name = StuffDao.getName(account);
			setName(name);
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getTime(){
		return superviseTime;
	}
}

package com.erp.entry;

import java.util.Arrays;
import java.util.List;

import com.erp.Dao.StuffDao;
import com.erp.utils.TimeUtils;

/**
 * string account
 * long assignment
 * string comment
 * String picture
 * long reportTime
 * int reportIndex
 * @author pc_home
 *
 */
public class ReportEntry {
	String reportId;
	String account;
	String assignmentId;
	String comment;
	List<String> picture;
	long reportTime;
	List<SuperviseEntry> supervise;
	int reportIndex;
	
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
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
		//		this.picture =  picture.split(";").;
	}
	public String getReportTime() {
		return TimeUtils.convert2String(reportTime);
	}
	public void setReportTime(long reportTime) {
		this.reportTime = reportTime;
	}
	public List<SuperviseEntry> getSupervise() {
		return supervise;
	}
	public void setSupervise(List<SuperviseEntry> supervise) {
		this.supervise = supervise;
	}
	public int getReportIndex() {
		return reportIndex;
	}
	public void setReportIndex(int reportIndex) {
		this.reportIndex = reportIndex;
	}
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	@Override
	public String toString() {
		return "ReportEntry [reportId=" + reportId + ", account=" + account + ", assignmentId=" + assignmentId
				+ ", comment=" + comment + ", picture=" + picture + ", reportTime=" + reportTime + ", supervise="
				+ supervise + ", reportIndex=" + reportIndex + "]\n";
	} 
	
	private String name = null;
	
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
	
	public boolean checkd;



	public boolean isCheckd() {
		return supervise.size() !=0;
	}
	
	public long getTime(){
		return reportTime;
	}
	
}
	
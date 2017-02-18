package com.erp.Entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.erp.utils.TimeUtils;

/**
 * create table Report(
	report_id bigint primary key auto_increment,
	time long not null,
	reportIndex int not null, 
    comment nvarchar(1000) not null,
    picture nvarchar(1000) not null,
    task_id bigint not null,
    foreign key(task_id) references Task(task_id) on delete cascade
);
 * @author ke.gao
 *
 */
public class ReportEntry {
	
	String reportId;
	String taskId;
	String comment;
	List<String> picture;
	String reportTime;
	int reportIndex;
	boolean outOfTime = false;
	
	public boolean isOutOfTime() {
		return outOfTime;
	}

	public void setOutOfTime(boolean outOfTime) {
		this.outOfTime = outOfTime;
	}



	public String getReportId() {
		return reportId;
	}



	public void setReportId(String reportId) {
		this.reportId = reportId;
	}



	public String getTaskId() {
		return taskId;
	}



	public void setTaskId(String taskId) {
		this.taskId = taskId;
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



	public void setPicture(String pictures) {
		picture =  Arrays.asList(pictures.split(";"));
	}



	public String getReportTime() {
		return TimeUtils.convert2String(reportTime);
	}



	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}



	public int getReportIndex() {
		return reportIndex;
	}



	public void setReportIndex(int reportIndex) {
		this.reportIndex = reportIndex;
	}



	@Override
	public String toString() {
		return "ReportEntry [reportId=" + reportId + ", taskId=" + taskId + ", comment=" + comment + ", picture="
				+ picture + ", reportTime=" + reportTime + ", reportIndex=" + reportIndex + "]";
	}

	
	
}

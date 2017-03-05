package com.erp.Entry;

public class ReportStarEntry {
	
	private String star;
	private String taskId;
	private int reportIndex;
	
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	
	
	public int getReportIndex() {
		return reportIndex;
	}
	public void setReportIndex(int reportIndex) {
		this.reportIndex = reportIndex;
	}
	@Override
	public String toString() {
		return "ReportStarEntry [star=" + star + ", taskId=" + taskId + ", reportIndex=" + reportIndex + "]";
	}
	
	
	
	
}

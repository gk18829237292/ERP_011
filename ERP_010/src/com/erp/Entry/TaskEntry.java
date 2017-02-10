package com.erp.Entry;

/**
 * 	task_id bigint primary key auto_increment,
    startTime long not null,
	endTime long not null,
    chairMan nvarchar(40) not null, -- 负责人
    type int not null, -- 0 不重要 1 重要
    place nvarchar(40),
    financing bigint not null,
    goal nvarchar(300) not null,
    report_type int not null, -- 0日报 1 周报，2半月报，3月报，4季报，5 半年报，6年报--
    department_id bigint not null,
 * @author pc_home
 *
 */
public class TaskEntry {
	
	private String taskId;
	private String taskName;
	private String startTime;
	private String endTime;
	private String chairMan;
	private String type;
	private String place;
	private String financing;
	private String goal;
	private String reportType;
	private DepartEntry depart;
	
	private int reportNum;
	private int advise1Num;
	private int advise2Num;
	
	public boolean isChecked1(){
		return reportNum == advise1Num;
	}
	
	public boolean isChecked2() {
		return reportNum == advise2Num;
	}
	
	public int getReportNum() {
		return reportNum;
	}
	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}

	public int getAdvise1Num() {
		return advise1Num;
	}
	public void setAdvise1Num(int advise1Num) {
		this.advise1Num = advise1Num;
	}
	public int getAdvise2Num() {
		return advise2Num;
	}
	public void setAdvise2Num(int advise2Num) {
		this.advise2Num = advise2Num;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getChairMan() {
		return chairMan;
	}
	public void setChairMan(String chairMan) {
		this.chairMan = chairMan;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getFinancing() {
		return financing;
	}
	public void setFinancing(String financing) {
		this.financing = financing;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public DepartEntry getDepart() {
		return depart;
	}
	public void setDepart(DepartEntry depart) {
		this.depart = depart;
	}
	
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public TaskEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TaskEntry [taskId=" + taskId + ", taskName=" + taskName + ", startTime=" + startTime + ", endTime="
				+ endTime + ", chairMan=" + chairMan + ", type=" + type + ", place=" + place + ", financing="
				+ financing + ", goal=" + goal + ", reportType=" + reportType  + ", reportNum="
				+ reportNum + ", advise1Num=" + advise1Num + ", advise2Num=" + advise2Num + "]\n";
	}
	
}

package com.erp.Entry;

import java.util.Date;
import java.util.Map;

import com.erp.utils.TimeUtils;

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
	private String updateTime;
	private String chairMan;
	private String type;
	private String place;
	private String financing;
	private String goal;
	private String reportType; // -- 0日报 1 周报，2半月报，3月报，4季报，5 半年报，6年报--
	private String departClassId;
	
	
	public String getDepartClassId() {
		return departClassId;
	}

	public void setDepartClassId(String departClassId) {
		this.departClassId = departClassId;
	}

	private int reportTimes;  // 总共需要的报告次数
	
	private int reportNum;	// 已经提交的报告次数
	private int advise1Num;
	private int advise2Num;
	
	private String departId;
	private String departName;
	
	private Map<Integer, ReportEntry> reports;
	private Map<Integer, AdviceEntry> advices1;
	private Map<Integer, AdviceEntry> advices2; 
	

	
	private boolean isComplete;
	private int progress;
	private boolean isAtTime;
	
	private boolean isInit = false;
	
	public Map<Integer, ReportEntry> getReports() {
		return reports;
	}

	public void setReports(Map<Integer, ReportEntry> reports) {
		this.reports = reports;
	}



	public Map<Integer, AdviceEntry> getAdvices1() {
		return advices1;
	}

	public void setAdvices1(Map<Integer, AdviceEntry> advices1) {
		this.advices1 = advices1;
	}

	public Map<Integer, AdviceEntry> getAdvices2() {
		return advices2;
	}

	public void setAdvices2(Map<Integer, AdviceEntry> advices2) {
		this.advices2 = advices2;
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


	//是否按期限 ？
	public boolean isAtTime() {
		int times = TimeUtils.getDays(new Date().getTime() - Long.parseLong(startTime)) / reportTypeToDays(Integer.parseInt(reportType));
		return times <= reportTimes;
	}

	public void setAtTime(boolean isAtTime) {
		this.isAtTime = isAtTime;
	}

	public static int reportTypeToDays(int reportType) {
		int ans = 1;
		switch (reportType) {
		case 0: ans = 1;break;
		case 1: ans = 7;break;
		case 2: ans = 15;break;
		case 3: ans = 30;break;
		case 4: ans = 90;break;
		case 5: ans = 180;break;
		case 6: ans = 365;break;
		}
		return ans;
	}
	
	public void init() {
		if(isInit) return;
		reportTimes = TimeUtils.getDays(Long.parseLong(endTime) - Long.parseLong(startTime)) / reportTypeToDays(Integer.parseInt(reportType));
		if(reports != null){
			for(ReportEntry entry:reports.values()){
				boolean result = (TimeUtils.getDays(Long.parseLong(entry.getReportTime()) - Long.parseLong(startTime)))  > (entry.getReportIndex() * reportTypeToDays(Integer.parseInt(reportType)));
				entry.setOutOfTime(result);
			}
		}
		isInit = true;
	}
	
	public boolean isComplete() {
		if(!isInit) init();
		setComplete(reportNum > reportTimes);
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public int getProgress() {
		if(reportTimes == 0) return 100;
		int temp = (int) (reportNum *100.0 / reportTimes);
		if(temp > 100) temp = 100;
		setProgress(temp);
		return temp;
	}


	public void setProgress(int progress) {
		this.progress = progress;
	}


	public String getUpdateTime() {
		return TimeUtils.convert2String(updateTime);
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	
	public boolean isChecked1(){
		return reportNum >= getNeedToReportNum();
	}
	
	public boolean isChecked2() {
		return reportNum == advise2Num && reportNum != 0;
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
		return TimeUtils.convert2String(startTime);
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return TimeUtils.convert2String(endTime);
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
		String ans = "日报";
		switch (reportType) {
		case "0": ans = "日报";break;
		case "1": ans = "周报";break;
		case "2": ans = "半月报";break;
		case "3": ans = "月报";break;
		case "4": ans = "季报";break;
		case "5": ans = "半年报";break;
		case "6": ans = "年报";break;
		}
		return ans;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public int getReportTimes() {
		return reportTimes;
	}

	public void setReportTimes(int reportTimes) {
		this.reportTimes = reportTimes;
	}

	public TaskEntry() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "TaskEntry [taskId=" + taskId + ", taskName=" + taskName + ", startTime=" + startTime + ", endTime="
				+ endTime + ", updateTime=" + updateTime + ", chairMan=" + chairMan + ", type=" + type + ", place="
				+ place + ", financing=" + financing + ", goal=" + goal + ", reportType=" + reportType
				+ ", reportTimes=" + reportTimes + ", reportNum=" + reportNum + ", advise1Num=" + advise1Num
				+ ", advise2Num=" + advise2Num + ", departId=" + departId + ", departName=" + departName + ", isComplete=" + isComplete
				+ ", progress=" + progress + ", isAtTime=" + isAtTime + ", isInit=" + isInit + ", needToReportNum="
				+ needToReportNum + "]";
	}

	public int getMaxNum() {
		int tmp = Math.max(reportNum, advise1Num);
		tmp = Math.max(tmp, advise2Num);
		tmp = Math.max(tmp, getNeedToReportNum());
	
		tmp = Math.min(tmp, 200);
		return tmp;
	}
	
	private int needToReportNum;

	public int getNeedToReportNum() {
		return TimeUtils.getDays(TimeUtils.getNowLongTime() - Long.parseLong(startTime)) / reportTypeToDays(Integer.parseInt(reportType));
	}

	public void setNeedToReportNum(int needToReportNum) {
		this.needToReportNum = needToReportNum;
	}

	
	
	

	
	
}

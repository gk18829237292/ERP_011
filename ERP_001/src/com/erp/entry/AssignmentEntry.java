package com.erp.entry;

import java.io.Serializable;

import com.erp.Dao.Assignment_DepartmentDao;
import com.erp.Dao.DepartmentDao;
import com.erp.Dao.StuffDao;
import com.erp.Log.Log;
import com.erp.utils.TimeUtils;

/**
 * long department_id
 * long startTime
 * long endTime
 * String createAccount
 * String place
 * long financing
 * String goal
 * int type
 * int num
 * int completNum
 * String assignmentName
 * @author pc_home
 *
 */
public class AssignmentEntry  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String TAG ="AssignmentEntry";
	private long assignment_id;
	private long startTime;		//
	private long endTime;		//
	private String createAccount;
	private String place;		//
	private long financing;	//
	private String goal;			//
	private int type;
	private int num;
	private int completNum;
	private String assignmentName;  //任务名称
	
	private String departmentName = null;
	private int processNum;
	private String accountName = null;
	private String departmentId = null;
	
	public AssignmentEntry() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public AssignmentEntry(long assignment_id, long startTime, long endTime, String createAccount, String place,
			long financing, String goal, int type, int num, int completNum, String assignmentName,
			String departmentName, int processNum, String accountName) {
		super();
		this.assignment_id = assignment_id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createAccount = createAccount;
		this.place = place;
		this.financing = financing;
		this.goal = goal;
		this.type = type;
		this.num = num;
		this.completNum = completNum;
		this.assignmentName = assignmentName;
		this.departmentName = departmentName;
		this.processNum = processNum;
		this.accountName = accountName;
	}



	public long getAssignment_id() {
		return assignment_id;
	}
	public void setAssignment_id(long assignment_id) {
		this.assignment_id = assignment_id;
	}
	public String getStartTime() {
		return TimeUtils.convert2String(startTime);
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return TimeUtils.convert2String(endTime);
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public String getCreateAccount() {
		return createAccount;
	}
	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public long getFinancing() {
		return financing;
	}
	public void setFinancing(long financing) {
		this.financing = financing;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCompletNum() {
		return completNum;
	}
	public void setCompletNum(int completNum) {
		this.completNum = completNum;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	
	@Override
	public String toString() {
		return "AssignmentEntry [assignment_id=" + assignment_id + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", createAccount=" + createAccount + ", place=" + place + ", financing=" + financing + ", goal="
				+ goal + ", type=" + type + ", num=" + num + ", completNum=" + completNum + ", assignmentName="
				+ assignmentName + "]";
	}
	
	public boolean haveComplete(){
		return completNum == num;
	}
	
	public String getProcessNum() {
		processNum =100;
		if(num != 0){
			processNum = (int) completNum  * 100 / num;
		}
		return processNum+"";
	}
	
	public String getDepartmentName() {
		if(departmentName == null){
			departmentName = Assignment_DepartmentDao.queryDepartmentName(assignment_id);
			setDepartmentName(departmentName);
		}
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getAccountName() {
		if(accountName == null){
			accountName = StuffDao.getName(createAccount);
			setAccountName(accountName);
		}
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getDepartmentId(){
		if(departmentId == null){
			departmentId =Assignment_DepartmentDao.queryDepartmentId(assignment_id+"");
		}
		return departmentId;
	}

	public long getTime_1(){
		return startTime;
	}
	public long getTime_2(){
		return endTime;
	}
	
	
}
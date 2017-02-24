package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Entry.DepartEntry;
import com.erp.Entry.TaskEntry;
import com.erp.Log.Log;
import com.erp.utils.DBUtils;
import com.sun.javafx.tk.Toolkit.Task;


public class TaskDao {

	private static final String TAG="TaskDao";
	private static final String TABLE_NAME="Task";
	
	
	
	public static List<TaskEntry> getAllTaskByDepartId() {
		List<TaskEntry> entries = new ArrayList<>();
		return entries;
	}
	
	public static List<TaskEntry> getAllTaskByDepartId(String departId,List<TaskEntry> taskEntries) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			return getAllTaskByDepartId(conn,departId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
		return null;
	}
	
	public static List<TaskEntry> getAllTaskByDepartId(Connection conn,String departId) {
		List<TaskEntry> taskEntries = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where department_id = ?" );
			stmt.setString(1, departId);
			rs = stmt.executeQuery();
			while(rs.next()){
				TaskEntry entry = fill(rs);
				taskEntries.add(entry);
			}

			for(TaskEntry entry:taskEntries){
				entry.setReportNum(ReportDao.getReportNum(conn, entry.getTaskId()));
				entry.setAdvise1Num(AdviceDao.getAdviceNum(conn, entry.getTaskId())); //监督者
				entry.setAdvise2Num(AdviceDao2.getAdviceNum(conn, entry.getTaskId())); //管理者
				entry.setDepartName(DepartDao.getDepartNameById(conn,entry.getDepartId()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		return taskEntries;
	}
	
	public static List<TaskEntry> getAllTaskByDepartId(String departId){
		List<TaskEntry> taskEntries = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where department_id = ?");
			stmt.setString(1, departId);
			rs = stmt.executeQuery();
			while(rs.next()){
				taskEntries.add(fill(rs));
			}
			for(TaskEntry entry:taskEntries){
				entry.setReportNum(ReportDao.getReportNum(conn, entry.getTaskId()));
				entry.setAdvise1Num(AdviceDao.getAdviceNum(conn, entry.getTaskId())); //监督者
				entry.setAdvise2Num(AdviceDao2.getAdviceNum(conn, entry.getTaskId())); //管理者
				entry.setDepartName(DepartDao.getDepartNameById(conn,entry.getDepartId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		return taskEntries;
	}
	
	public static List<TaskEntry> getAllTask(int num){
		List<TaskEntry> taskEntries = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from "+ TABLE_NAME +" order by updateTime desc limit ?");
			stmt.setInt(1, num);
			rs = stmt.executeQuery();
			while(rs.next()){
				taskEntries.add(fill(rs));
			}
			
			for(TaskEntry entry:taskEntries){
				entry.setReportNum(ReportDao.getReportNum(conn, entry.getTaskId()));
				entry.setAdvise1Num(AdviceDao.getAdviceNum(conn, entry.getTaskId())); //监督者
				entry.setAdvise2Num(AdviceDao2.getAdviceNum(conn, entry.getTaskId())); //管理者
				entry.setDepartName(DepartDao.getDepartNameById(conn,entry.getDepartId()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		return taskEntries;
	}
	
	public static TaskEntry getTaskById(String taskId){
		TaskEntry taskEntry = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from "+ TABLE_NAME +" where task_id = ?");
			stmt.setString(1, taskId);
			rs = stmt.executeQuery();
			if(!rs.first()) return taskEntry;
			taskEntry = fill(rs);
			
			taskEntry.setReportNum(ReportDao.getReportNum(conn, taskEntry.getTaskId()));
			taskEntry.setAdvise1Num(AdviceDao.getAdviceNum(conn, taskEntry.getTaskId())); //监督者
			taskEntry.setAdvise2Num(AdviceDao2.getAdviceNum(conn, taskEntry.getTaskId())); //管理者
			taskEntry.setDepartName(DepartDao.getDepartNameById(conn,taskEntry.getDepartId()));
			
			taskEntry.setReports(ReportDao.getAllReportByTaskId_Map(conn,taskEntry.getTaskId()));
			taskEntry.setAdvices1(AdviceDao.getAllAdviceByTaskId_Map(conn,taskEntry.getTaskId()));
			taskEntry.setAdvices2(AdviceDao2.getAllAdviceByTaskId_Map(conn,taskEntry.getTaskId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		return taskEntry;
	}
	
 	public static TaskEntry fill(ResultSet rs) throws SQLException {
		TaskEntry entry = new TaskEntry();
		entry.setTaskId(rs.getString("task_id"));
		entry.setTaskName(rs.getString("task_name"));
		entry.setStartTime(rs.getString("startTime"));
		entry.setEndTime(rs.getString("endTime"));
		entry.setUpdateTime(rs.getString("updateTime"));
		entry.setChairMan(rs.getString("chairMan"));
		entry.setType(rs.getString("type"));
		entry.setPlace(rs.getString("place"));
		entry.setFinancing(rs.getString("financing"));
		entry.setGoal(rs.getString("goal"));
		entry.setReportType(rs.getString("report_type"));
		entry.setDepartId(rs.getString("department_id"));
		entry.init();
		return entry;
	}
 	
 	public static String getDepartIdByTaskId(String taskId){
 		String result = "";
 		Connection conn = null;
 		PreparedStatement stmt = null;
 		ResultSet rs = null;
 		
 		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select department_id from " + TABLE_NAME + " where task_id = ?");
			stmt.setString(1, taskId);
			rs = stmt.executeQuery();
			if(rs.first())
				return rs.getString("department_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
 		
 		return result;
 	}
 	//task_name,startTime,endTime,updateTime,chairMan,type,place,financing,goal,report_type,department_id，picture
 	public static boolean insert(String taskName,long startTime,long endTime,long updateEndTime,String chairMan,String type,String place,String finan,String goal,String reportType,String departId,String picture){
 		boolean result = false;
 		Connection conn = null;
 		PreparedStatement stmt = null;
 		
 		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " (task_name,startTime,endTime,updateEndTime,chairMan,type,place,financing,goal,report_type,department_id,picture) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, taskName);
			stmt.setLong(2, startTime);
			stmt.setLong(3,endTime);
			stmt.setLong(4,updateEndTime);
			stmt.setString(5, chairMan);
			stmt.setString(6, type);
			stmt.setString(7, place);
			stmt.setString(8, finan);
			stmt.setString(9, goal);
			stmt.setString(10,reportType);
			stmt.setString(11, departId);
			stmt.setString(12, picture);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
 		
 		
 		return result;
 	}
	
	public static long getNextId(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = ?");
			stmt.setString(1, TABLE_NAME);
			rs = stmt.executeQuery();
			rs.first();
			return rs.getLong(1);
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt,conn);
		}
		return -1;
	}
	
	public static boolean update(String taskId,String taskName,long startTime,long endTime,long updateEndTime,String chairMan,String type,String place,String finan,String goal,String reportType,String departId,String picture){
 		boolean result = false;
 		Connection conn = null;
 		PreparedStatement stmt = null;
 		
 		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " set task_name=?,startTime=?,endTime=?,updateEndTime=?,chairMan=?,type=?,place=?,financing=?,goal=?,report_type=?,department_id=?,picture=? where task_id =?");
			stmt.setString(1, taskName);
			stmt.setLong(2, startTime);
			stmt.setLong(3,endTime);
			stmt.setLong(4,updateEndTime);
			stmt.setString(5, chairMan);
			stmt.setString(6, type);
			stmt.setString(7, place);
			stmt.setString(8, finan);
			stmt.setString(9, goal);
			stmt.setString(10,reportType);
			stmt.setString(11, departId);
			stmt.setString(12, picture);
			stmt.setString(13, taskId);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
 		
 		
 		return result;
 	}
	

}
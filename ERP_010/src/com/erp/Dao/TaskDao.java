package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Entry.DepartEntry;
import com.erp.Entry.TaskEntry;
import com.erp.utils.DBUtils;


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
				entry.setAdvise1Num(AdviceDao.getAdviceNum(conn, entry.getTaskId(), 0)); //监督者
				entry.setAdvise2Num(AdviceDao.getAdviceNum(conn, entry.getTaskId(), 1)); //管理者
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
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
				entry.setAdvise1Num(AdviceDao.getAdviceNum(conn, entry.getTaskId(), 0)); //监督者
				entry.setAdvise2Num(AdviceDao.getAdviceNum(conn, entry.getTaskId(), 1)); //管理者
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		return taskEntries;
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
		return entry;
	}
	
}

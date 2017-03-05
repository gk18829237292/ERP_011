package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.erp.Entry.ReportEntry;
import com.erp.utils.DBUtils;

import sun.nio.cs.FastCharsetProvider;
import sun.util.logging.resources.logging_zh_TW;

public class ReportDao {
	
	private static final String TAG="ReportDao";
	private static final String TABLE_NAME="Report";
	
	public static int getReportNum(String taskId){
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			return getReportNum(conn,taskId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
		return -1;
	}
	
	public static int getReportNum(Connection conn,String taskId){
		int ans = -1;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("select count(*) from " + TABLE_NAME +" where task_id = ?");
			stmt.setString(1, taskId);
			rs = stmt.executeQuery();
			if(rs.first()){
				ans = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		
		return ans;
	}
	
	public static boolean checkByTaskId(String taskId){
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			return checkByTaskId(taskId,conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
		return false;
	}
	
	public static boolean checkByTaskId(String taskId,Connection conn){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where task_id = ?");
			stmt.setString(1, taskId);
			rs = stmt.executeQuery();
			return rs.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		
		return false;
	}
	
	public static List<ReportEntry> getAllReportByTaskId(String taskId) {
		List<ReportEntry> reportEntries = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where task_id = ?");
			stmt.setString(1, taskId);
			rs = stmt.executeQuery();
			while(rs.next()){
				reportEntries.add(fill(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs,stmt,conn);
		}
		return reportEntries;
	}
	public static Map<Integer, ReportEntry> getAllReportByTaskId_Map(Connection conn,String taskId) {
		Map<Integer, ReportEntry> reportEntries = new HashMap<Integer,ReportEntry>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where task_id = ?");
			stmt.setString(1, taskId);
			rs = stmt.executeQuery();
			while(rs.next()){
				ReportEntry entry = fill(rs);
				reportEntries.put(entry.getReportIndex(), entry);
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs,stmt);
		}
		return reportEntries;
	}
	public static Map<Integer, ReportEntry> getAllReportByTaskId_Map(String taskId) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			return getAllReportByTaskId_Map(conn, taskId);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
		return null;
	}
	
	/**
	 * create table Report(
	time long not null,
	reportIndex int not null, 
    comment nvarchar(1000) not null,
    picture nvarchar(1000) not null,
    task_id bigint not null,
    foreign key(task_id) references Task(task_id) on delete cascade
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	public static ReportEntry fill(ResultSet rs) throws SQLException {
		ReportEntry entry = new ReportEntry();
		entry.setComment(rs.getString("comment"));
		entry.setPicture(rs.getString("picture"));
		entry.setReportIndex(rs.getInt("reportIndex"));
		entry.setReportTime(rs.getString("time"));
		entry.setTaskId(rs.getString("task_id"));
		return entry;
	}
	
	/**
	 * time long not null,
	reportIndex int not null, 
    comment nvarchar(1000) not null,
    picture nvarchar(1000) not null,
    task_id bigint not null,
	 * @return
	 */
	public static boolean insert(String time,String reportIndex,String comment,String picture,String task_id){
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtils.getConnection();
			delete(conn, reportIndex, task_id);
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?,?,?,?)");
			stmt.setString(1, time);
			stmt.setString(2, reportIndex);
			stmt.setString(3, comment);
			stmt.setString(4, picture);
			stmt.setString(5, task_id);
			result = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
		return result;
	}
	
	public static boolean delete(Connection conn, String reportIndex,String task_id){
		boolean result = false;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("delete from " + TABLE_NAME + " where reportIndex = ? and task_id = ?");
			stmt.setString(1, reportIndex);
			stmt.setString(2, task_id);
			result = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt);
		}
		return result;
	}
}

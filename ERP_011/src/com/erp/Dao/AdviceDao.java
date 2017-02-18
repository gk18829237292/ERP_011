package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.erp.Entry.AdviceEntry;
import com.erp.Entry.ReportEntry;
import com.erp.utils.DBUtils;

public class AdviceDao {
	
	private static final String TAG="AdviceDao";
	private static final String TABLE_NAME="Advice";
	
	public static int getAdviceNum(Connection conn,String taskId,int type){
		int ans = -1;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("select count(*) from " + TABLE_NAME +" where task_id = ? and type = ?");
			stmt.setString(1, taskId);
			stmt.setInt(2, type);
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
	
	public static boolean checkByTaskId(String taskId,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("");
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
	
	public static Map<Integer, AdviceEntry> getAllAdviceByTaskId_Map(Connection conn,String taskId,int type){
		Map<Integer, AdviceEntry> adviceEntries = new HashMap<Integer,AdviceEntry>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where task_id = ? and type = ?");
			stmt.setString(1, taskId);
			stmt.setInt(2, type);
			rs = stmt.executeQuery();
			while(rs.next()){
				AdviceEntry entry = fill(rs);
				adviceEntries.put(entry.getAdviceIndex(), entry);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs,stmt);
		}
		return adviceEntries;
	}
	
	public static Map<Integer, AdviceEntry> getAllAdviceByTaskId_Map(String taskId,int type){
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			return getAllAdviceByTaskId_Map(conn,taskId,type);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
		return null;
	}
	
	/**
	 * create table Advice(
	advice_id bigint primary key auto_increment,
    time long not null,
    adviceIndex int not null,
    comment nvarchar(1000) not null,
    picture nvarchar(1000) not null,
    task_id bigint not null,
    type int not null,
    foreign key(task_id) references Task(task_id) on delete cascade
);
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	public static AdviceEntry fill(ResultSet rs) throws SQLException{
		AdviceEntry entry = new AdviceEntry();
		entry.setAdviceId(rs.getString("advice_id"));
		entry.setAdviceIndex(rs.getInt("adviceIndex"));
		entry.setCommment(rs.getString("comment"));
		entry.setPicture(rs.getString("picture"));
		entry.setTaskId(rs.getString("task_id"));
		entry.setTime(rs.getString("time"));
		entry.setType(rs.getInt("type"));
		return entry;
	}
	
}

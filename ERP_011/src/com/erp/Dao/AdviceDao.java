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
import com.sun.org.apache.regexp.internal.recompile;

public class AdviceDao {
	
	private static final String TAG="AdviceDao";
	private static final String TABLE_NAME="Advice";
	
	public static int getAdviceNum(Connection conn,String taskId){
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
	
	public static Map<Integer, AdviceEntry> getAllAdviceByTaskId_Map(Connection conn,String taskId){
		Map<Integer, AdviceEntry> adviceEntries = new HashMap<Integer,AdviceEntry>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where task_id = ?");
			stmt.setString(1, taskId);
			rs = stmt.executeQuery();
			while(rs.next()){
				AdviceEntry entry = fill(rs);
				adviceEntries.put(entry.getAdviceIndex(), entry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs,stmt);
		}
		return adviceEntries;
	}
	
	public static Map<Integer, AdviceEntry> getAllAdviceByTaskId_Map(String taskId){
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			return getAllAdviceByTaskId_Map(conn,taskId);
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
		entry.setAdviceIndex(rs.getInt("adviceIndex"));
		entry.setCommment(rs.getString("comment"));
		entry.setPicture(rs.getString("picture"));
		entry.setTaskId(rs.getString("task_id"));
		entry.setTime(rs.getString("time"));
		entry.setStar(rs.getInt("star"));
		return entry;
	}
	/**
	 *     time long not null,
    adviceIndex int not null,
    comment nvarchar(1000) not null,
    picture nvarchar(1000) not null,
    task_id bigint not null,
    foreign key(task_id) references Task(task_id) on delete cascade,
    primary key (adviceIndex,task_id)
	 * @return
	 */
	public static boolean insert_withDelete(String time,String adviceIndex,String comment,String picture,String taskId,String star){
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			delete(conn, adviceIndex, taskId);
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?,?,?,?,?)");
			stmt.setString(1, star);
			stmt.setString(2, time);
			stmt.setString(3, adviceIndex);
			stmt.setString(4, comment);
			stmt.setString(5, picture);
			stmt.setString(6, taskId);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
		return result;
	}
	
	public static boolean delete(Connection conn,String adviceIndex,String taskId) {
		boolean result = false;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("delete from " + TABLE_NAME + " where adviceIndex = ? and task_id = ?");
			stmt.setString(1, adviceIndex);
			stmt.setString(2, taskId);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt);
		}
		return result;
	}
}

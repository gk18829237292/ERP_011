package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.erp.Entry.AdviceEntry;
import com.erp.utils.DBUtils;

public class AdviceDao2 {

	private static final String TAG="AdviceDao";
	private static final String TABLE_NAME="Advice_2";
	
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
	
	public static AdviceEntry fill(ResultSet rs) throws SQLException{
		AdviceEntry entry = new AdviceEntry();
		entry.setAdviceIndex(rs.getInt("adviceIndex"));
		entry.setCommment(rs.getString("comment"));
		entry.setTaskId(rs.getString("task_id"));
		entry.setName(rs.getString("name"));
		return entry;
	}
	
	public static boolean insert_withDelete(String time,String adviceIndex,String name, String comment,String taskId){
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			delete(conn, adviceIndex, taskId);
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?,?,?,?)");
			stmt.setString(1, time);
			stmt.setString(2, adviceIndex);
			stmt.setString(3, name);
			stmt.setString(4, comment);
			stmt.setString(5, taskId);
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

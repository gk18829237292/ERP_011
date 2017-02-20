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
		entry.setAdviceId(rs.getString("advice_id"));
		entry.setAdviceIndex(rs.getInt("adviceIndex"));
		entry.setCommment(rs.getString("comment"));
		entry.setTaskId(rs.getString("task_id"));
		entry.setName(rs.getString("name"));
		return entry;
	}
	
}

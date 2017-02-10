package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.erp.utils.DBUtils;

public class ReportDao {
	
	private static final String TAG="ReportDao";
	private static final String TABLE_NAME="Report";
	
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
	
}

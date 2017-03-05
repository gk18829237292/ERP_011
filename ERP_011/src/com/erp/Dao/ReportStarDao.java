package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.erp.Entry.ReportEntry;
import com.erp.Entry.ReportStarEntry;
import com.erp.utils.DBUtils;

import sun.security.timestamp.TSRequest;

public class ReportStarDao {
	
	private static final String TAG="ReportStarDao";
	private static final String TABLE_NAME="ReportStar";
	
	public static int getStarByIdAndIndex(Connection conn,String taskId,String reportIndex){
		int result = -1;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where task_id = ? and reportIndex = ? ");
			stmt.setString(1, taskId);
			stmt.setString(2, reportIndex);
			rs = stmt.executeQuery();
			if(rs.first())
				result = rs.getInt("star");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		return result;
	}
	
	public static boolean insert(String star,String taskId,String reportIndex) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?,?)");
			stmt.setString(1, star);
			stmt.setString(2, taskId);
			stmt.setString(3, reportIndex);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
		return result;
	}
	
	public static Map<Integer, ReportStarEntry> getAllReportByTaskId_Map(Connection conn,String taskId) {
		Map<Integer, ReportStarEntry> reportEntries = new HashMap<Integer,ReportStarEntry>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where task_id = ?");
			stmt.setString(1, taskId);
			rs = stmt.executeQuery();
			while(rs.next()){
				ReportStarEntry entry = fill(rs);
				reportEntries.put(entry.getReportIndex(), entry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs,stmt);
		}
		return reportEntries;
	}
	
	
	public static ReportStarEntry fill(ResultSet rs) throws SQLException{
		ReportStarEntry entry  = new ReportStarEntry();
		entry.setStar(rs.getString("star"));
		entry.setTaskId(rs.getString("task_id"));
		entry.setReportIndex(rs.getInt("reportIndex"));
		return entry;
	}
}

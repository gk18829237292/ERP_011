package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Log.Log;
import com.erp.entry.AssignmentEntry;
import com.erp.entry.ReportEntry;
import com.erp.utils.DBUtils;

/**
 * string account
 * long assignment
 * string comment
 * String picture
 * long reportTime
 * int reportIndex
 * @author pc_home
 *
 */
public class ReportDao {
	private static final String TAG="ReportDao";
	private static final String TABLE_NAME="Report";
	
	public static int insert(String account,String taskId,String comment,
			String picture,long reportTime,String index){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " (account, assignment_id,comment,picture,reportTime,reportIndex) values(?,?,?,?,?,?)");
			stmt.setString(1, account);
			stmt.setString(2, taskId);
			stmt.setString(3, comment);
			stmt.setString(4, picture);
			stmt.setLong(5, reportTime);
			stmt.setString(6, index);
			stmt.execute();
			Log.logInfo(TAG, "insert Success");
			return 1;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(stmt, conn);
		}
		
		return 0;
	}

	public static List<ReportEntry> getAllReport(String assignmentId){
		List<ReportEntry> entrys = new ArrayList<ReportEntry>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn =DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " +TABLE_NAME + " where assignment_id = ?" );
			stmt.setString(1, assignmentId);
			rs = stmt.executeQuery();
			while(rs.next()){
				entrys.add(fill(rs));
			}
			DBUtils.close(rs, stmt, conn);
			SuperviseDao.fillReportList(entrys);
			return entrys;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
			DBUtils.close(rs, stmt, conn);
		}finally {
			
			
		}
		
		return null;
	}
	
	public static List<ReportEntry> getAllReport(String assignmentId,String account){
		List<ReportEntry> entrys = new ArrayList<ReportEntry>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn =DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " +TABLE_NAME + " where assignment_id = ? and account = ?" );
			stmt.setString(1, assignmentId);
			stmt.setString(2, account);
			rs = stmt.executeQuery();
			while(rs.next()){
				entrys.add(fill(rs));
			}
			DBUtils.close(rs, stmt, conn);
			SuperviseDao.fillReportList(entrys);
			return entrys;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
			DBUtils.close(rs, stmt, conn);
		}finally {
			
			
		}
		
		return null;
	}
	
	private static ReportEntry fill(ResultSet rs) throws SQLException{
		ReportEntry entry = new ReportEntry();
		entry.setReportId(rs.getString("report_id"));
		entry.setAccount(rs.getString("account"));
		entry.setComment(rs.getString("comment"));
		entry.setPicture(rs.getString("picture"));
		entry.setReportTime(rs.getLong("reportTime"));
		entry.setReportIndex(rs.getInt("reportIndex"));
		return entry;
	}
}

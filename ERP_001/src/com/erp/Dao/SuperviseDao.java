package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Log.Log;
import com.erp.entry.ReportEntry;
import com.erp.entry.SuperviseEntry;
import com.erp.utils.DBUtils;
/**
 * long supervise_id
 * long report_id
 * String account
 * String comment
 * String picture
 * long supervistTime
 * @author pc_home
 *
 */
public class SuperviseDao {
	
	private static final String TAG="SuperviseDao";
	private static final String TABLE_NAME="Supervise";
	
	public static int insert(String string,String account,String comment,String picture,long superviseTime){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " +TABLE_NAME + " (report_id,account,comment,picture,superviseTime) values(?,?,?,?,?)" );
			//设置参数
			stmt.setString(1, string);
			stmt.setString(2, account);
			stmt.setString(3, comment);
			stmt.setString(4, picture);
			stmt.setLong(5, superviseTime);
			//执行
			stmt.execute();
			Log.logInfo(TAG, "insert success");
			return 1;
		}catch(SQLException e){
			Log.logError(TAG, e.getMessage());
		}finally{
			DBUtils.close(stmt, conn);
		}
		return 0;
	}
	
	public static List<SuperviseEntry> getAllSupervise(String reportId){
		List<SuperviseEntry> entrys = new ArrayList<SuperviseEntry>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where report_id =?");
			stmt.setString(1, reportId);
			rs = stmt.executeQuery();
			while(rs.next()){
				entrys.add(fill(rs));
			}
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		
		return null;
	}
	
	public static void fillReportList(List<ReportEntry> entrys){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			for(ReportEntry report : entrys){
				stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where report_id =?");
				stmt.setString(1, report.getReportId());
				rs = stmt.executeQuery();
				List<SuperviseEntry> list = new ArrayList<SuperviseEntry>();
				while(rs.next()){
					list.add(fill(rs));
				}
				report.setSupervise(list);
			}
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
	}
	
	private static SuperviseEntry fill(ResultSet rs) throws SQLException{
		SuperviseEntry entry = new SuperviseEntry();
		entry.setSupervise_id(rs.getString("supervise_id"));
		entry.setReport_id(rs.getString("report_id"));
		entry.setAccount(rs.getString("account"));
		entry.setComment(rs.getString("comment"));
		entry.setPicture(rs.getString("picture"));
		entry.setSuperviseTime(rs.getLong("superviseTime"));
		
		return entry;
	}
}

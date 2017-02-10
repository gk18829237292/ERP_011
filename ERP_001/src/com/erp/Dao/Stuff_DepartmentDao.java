package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.erp.Log.Log;
import com.erp.entry.AssignmentEntry;
import com.erp.utils.DBUtils;

/**
 * String account
 * long department_id
 * String name
 * @author pc_home
 *
 */
public class Stuff_DepartmentDao {
	private static final String TAG="Stuff_DepartmentDao";
	private static final String TABLE_NAME="Stuff_Department";
	
	public static int insert(String account,long department_id,String name){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?,?)");
			stmt.setString(1, account);
			stmt.setLong(2, department_id);
			stmt.setString(3, name);
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
	
	public static long queryDepartmentId(String stuffId){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + "  where account = ?");
			stmt.setString(1, stuffId);
			rs = stmt.executeQuery();
			rs.first();
			return rs.getLong("department_id");
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		
		return -1;
	}
	
	
}

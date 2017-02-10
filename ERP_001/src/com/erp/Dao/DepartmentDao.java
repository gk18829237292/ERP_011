package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Log.Log;
import com.erp.entry.DepartmentEntry;
import com.erp.utils.DBUtils;
import com.mysql.jdbc.Statement;

/**
 * long departmentid
 * string name
 * int type
 * @author pc_home
 *
 */
public class DepartmentDao {
	
	private static final String TAG="DepartmentDao";
	private static final String TABLE_NAME="Department";
	
	public static int insert(long departmentId,String name,int type){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?,?)");
			stmt.setLong(1, departmentId);
			stmt.setString(2, name);
			stmt.setInt(3, type);
			stmt.execute();
			return 1;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(stmt, conn);
		}
		
		return 0;
	}

	public static void insert(String name){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME +"  (department_name,department_type) values(?,1)");
			stmt.setString(1, name);
			stmt.execute();
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(stmt, conn);
		}
	}
	
	public static List<DepartmentEntry> getAllDepartment(){
		List<DepartmentEntry> entrys = new ArrayList<DepartmentEntry>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME);
			
			rs= stmt.executeQuery();
			
			while(rs.next()){
				
				entrys.add(fill(rs));
			}
			return entrys;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		
		return  null;
	}
	
	public static DepartmentEntry getDepartment(String departmentId){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " +TABLE_NAME + " where department_id = ?");
			stmt.setString(1, departmentId);
			rs = stmt.executeQuery();
			rs.first();
			return fill(rs);
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		
		return null;
	}
	
	private static DepartmentEntry fill(ResultSet rs) throws SQLException{
		DepartmentEntry entry = new DepartmentEntry();
		entry.setDepartmentId(rs.getString("department_id"));
		entry.setDepartmentName(rs.getString("department_name"));
		entry.setType(rs.getInt("department_type"));
		
		return entry;
	}
	
	public static boolean query(String departName){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where department_name = ?");
			stmt.setString(1, departName);
			rs = stmt.executeQuery();
			return rs.first();
//			return stmt.execute();
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(stmt, conn);
		}
		return true;
	}
	
	public static void deleteDepart(String departId){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			//TODO 试着用触发器去解决
			stmt = conn.prepareStatement("delete from " + TABLE_NAME + " where department_id = ?");
			stmt.setString(1, departId);
			stmt.execute();
			return;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(stmt, conn);
		}
		
		return;
		
	}
	
	public static void update(String departId,String name){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("update " + TABLE_NAME + " set department_name =? where department_id =?   ");
			stmt.setString(2, departId);
			stmt.setString(1, name);
			stmt.execute();
			return;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(stmt, conn);
		}
		
		return;
	}
}

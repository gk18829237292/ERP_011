package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.erp.Log.Log;
import com.erp.entry.AssignmentEntry;
import com.erp.utils.DBUtils;

/**
 * long assignment_id
 * long department_id
 * @author pc_home
 *
 */
public class Assignment_DepartmentDao {
	
	private static final String TAG="Assignment_DepartmentDao";
	private static final String TABLE_NAME="Assignment_Department";
	
	public static int insert(String string,String string2){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?)");
			stmt.setString(1, string);
			stmt.setString(2, string2);
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

	public static List<AssignmentEntry> getAssignmentByDepartmentId(String departmentId){
		
		List<AssignmentEntry> entrys = new ArrayList<AssignmentEntry>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from assignment,assignment_department where assignment.assignment_id = assignment_department.assignment_id and department_id = ?"); 
			stmt.setString(1, departmentId);
			rs = stmt.executeQuery();
			while(rs.next()){
				//(startTime,endTime,createAccount,place,financing,goal,assignment_type,num,completeNum,assignment_name
				AssignmentEntry entry = new AssignmentEntry(); 
				entry.setAssignment_id(rs.getLong(1));
				entry.setStartTime(rs.getLong("startTime"));
				entry.setEndTime(rs.getLong("endTime"));
				entry.setCreateAccount(rs.getString("createAccount"));
				entry.setPlace(rs.getString("place"));
				entry.setFinancing(rs.getLong("financing"));
				entry.setGoal(rs.getString("goal"));
				entry.setType(rs.getInt("assignment_type"));
				entry.setNum(rs.getInt("num"));
				entry.setCompletNum(rs.getInt("completeNum"));
				entry.setAssignmentName(rs.getString("assignment_name"));
				entrys.add(entry);
			}
			return entrys;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage() +"  " + Arrays.toString(e.getStackTrace()));
//			System.out.println(e.);
			e.printStackTrace();
		}finally {
			DBUtils.close(rs,stmt, conn);
		}
		
		return null;
	}
	
	public static String queryDepartmentName(long assignmentId){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from assignment,assignment_department,department where assignment.assignment_id = assignment_department.assignment_id and assignment_department.department_id = department.department_id and assignment.assignment_id = ?");
			stmt.setLong(1, assignmentId);
			rs = stmt.executeQuery();
			String name = null;
			if(rs.first()){
				name= rs.getString("department_name");
			}
			return name;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		return null;
	}
	
	public static int update(String assignment_id,String department_id){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("update " + TABLE_NAME + " set department_id = ? where assignment_id = ?");
			stmt.setString(1, department_id);
			stmt.setString(2, assignment_id);
			stmt.execute();
			return 1;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(stmt, conn);
		}
		
		return 0;
	}

	public static String queryDepartmentId(String taskId){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select department_id from " + TABLE_NAME + " where assignment_id = ?");
			stmt.setString(1, taskId);
			rs = stmt.executeQuery();
			rs.first();
			return rs.getString("department_id");
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		
		return "";
	}
}










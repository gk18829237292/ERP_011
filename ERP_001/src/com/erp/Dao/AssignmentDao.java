package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Log.Log;
import com.erp.entry.AssignmentEntry;
import com.erp.utils.DBUtils;

/**
 * long department_id
 * long startTime
 * long endTime
 * String createAccount
 * String place
 * long financing
 * String goal
 * int type
 * int num
 * int completNum
 * String assignmentName
 * @author pc_home
 *
 */
public class AssignmentDao {
	
	private static final String TAG="AssignmentDao";
	private static final String TABLE_NAME="Assignment";
	
	public static int insert(long startTime,long endTime,String createAccount,String place,String financing,String goal,String tasktype,String num,String assignmentName){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " (startTime,endTime,createAccount,place,financing,goal,assignment_type,num,completeNum,assignment_name) values(?,?,?,?,?,?,?,?,?,?)");
			stmt.setLong(1, startTime);
			stmt.setLong(2, endTime);
			stmt.setString(3, createAccount);
			stmt.setString(4, place);
			stmt.setString(5, financing);
			stmt.setString(6, goal);
			stmt.setString(7, tasktype);
			stmt.setString(8, num);
			stmt.setInt(9, 0);
			stmt.setString(10, assignmentName);
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

	public static List<AssignmentEntry> getAllAssignment(){
		List<AssignmentEntry> entrys = new ArrayList<AssignmentEntry>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME); 
			rs = stmt.executeQuery();
			while(rs.next()){
				entrys.add(fill(rs));
			}
			return entrys;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs,stmt, conn);
		}
		return null;
	}
	
	public static AssignmentEntry getAssignment(String assignmentId){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where assignment_id = ?");
			stmt.setString(1, assignmentId);
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

	public static int update(long startTime,long endTime,String place,String financing,
			String goal,String tasktype, String num,String assignmentName,String taskId){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("update " + TABLE_NAME +" set startTime = ?,endTime=?,place=?,financing=?,goal=?,assignment_type=?,num=?,assignment_name=? where assignment_id =? ");
			stmt.setLong(1, startTime);
			stmt.setLong(2, endTime);
			stmt.setString(3, place);
			stmt.setString(4, financing);
			stmt.setString(5, goal);
			stmt.setString(6, tasktype);
			stmt.setString(7, num);
			stmt.setString(8, assignmentName);
			stmt.setString(9, taskId);
			stmt.execute();
			Log.logInfo(TAG, "update success");
			return 1;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(stmt, conn);
		}
		return 0;
	}
	
	public static long getNextId(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = ?");
			stmt.setString(1, TABLE_NAME);
			rs = stmt.executeQuery();
			rs.first();
			return rs.getLong(1);
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		return -1;
	}
	
	public static int deleteTask(String taskId){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtils.getConnection();
			//TODO 试着用触发器去解决
			stmt = conn.prepareStatement("delete from " + TABLE_NAME + " where assignment_id = ?");
			stmt.setString(1, taskId);
			stmt.execute();
			return 1;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(stmt, conn);
		}
		
		return 0;
	}
	
	/**
	 * 获取所有的自己提交过的任务
	 * @param account
	 * @return
	 */
	public static List<AssignmentEntry> getAllAssignmentByAccount(String account){
		List<AssignmentEntry> entrys = new ArrayList<AssignmentEntry>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from assignment,report where report.account =? and report.assignment_id = assignment.assignment_id group by assignment.assignment_id");
			stmt.setString(1, account);
			rs = stmt.executeQuery();
			while(rs.next()){
				entrys.add(fill(rs));
			}
			return entrys;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally{
			//TODO 测试执行那个先执行
			DBUtils.close(rs, stmt, conn);
		}
		return entrys;
	}
	
	public static void updateIfSmall(String taskId,int num){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select completeNum from " + TABLE_NAME + " where assignment_id = ?");
			stmt.setString(1, taskId);
			rs = stmt.executeQuery();
			rs.first();
			if(rs.getInt(1) < num){
				stmt = conn.prepareStatement("update " + TABLE_NAME + " set completeNum =? where assignment_id = ?");
				stmt.setInt(1, num);
				stmt.setString(2, taskId);
				stmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		
	}
	
	private static AssignmentEntry fill(ResultSet rs) throws SQLException{
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
		return entry;
	}
}







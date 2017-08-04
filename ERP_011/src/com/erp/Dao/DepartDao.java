package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Entry.DepartClassEntry;
import com.erp.Entry.DepartEntry;
import com.erp.Entry.TaskEntry;
import com.erp.Log.Log;
import com.erp.utils.DBUtils;
import com.mysql.jdbc.Statement;

import jdk.internal.org.objectweb.asm.util.TraceSignatureVisitor;


public class DepartDao {
	
	private static final String TAG="DepartDao";
	private static final String TABLE_NAME="Depart";
	

	
	public static String getDepartNameById(Connection conn,String departId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt =  conn.prepareStatement("select * from " + TABLE_NAME + " where department_id = ?");
			stmt.setString(1, departId);
			rs = stmt.executeQuery();
			if (rs.first()) {
				return fill(rs).getDepartName();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		return "";
	}
	
	public static String getDepartNameById(String departId){
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			return getDepartNameById(conn,departId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
		return "";
	}
	
	public static List<DepartEntry> getAllDepart(){
		List<DepartEntry> departEntries = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt =  conn.prepareStatement("select * from " + TABLE_NAME);
			rs = stmt.executeQuery();
			while(rs.next()){
				departEntries.add(fill(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		
		return departEntries;
	}
	
	public static List<DepartEntry> getAllDepartByClassId(String departClassId){
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			return getAllDepartByClassId(conn, departClassId);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
		
		return null;
		
	}
	
	private static String change2String(List<String> departIDs) {
		StringBuilder sb = new StringBuilder();
		for(String departId:departIDs){
			sb.append(departId);
			sb.append(",");
		}
		String str = sb.toString();
		System.out.println(str.substring(0,str.length()-1));
		return str.substring(0, str.length()-1);
	}
	
	
	public static List<DepartEntry> getAllDepartByClassId(Connection conn,String departClassId,List<String> departIDs){
		List<DepartEntry> departEntries = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt =  conn.prepareStatement("select * from Depart,DepartClass,Depart_DepartClass where Depart.department_id = Depart_DepartClass.department_id and Depart_DepartClass.departClass_id = DepartClass.departClass_id and DepartClass.departClass_id = ?");
			stmt.setString(1, departClassId);
			rs = stmt.executeQuery();
			while(rs.next()){
				DepartEntry entry = fill(rs);
				if(departIDs.contains(entry.getDepartId())){
					departEntries.add(entry);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		
		return departEntries;
	}
	
	public static List<DepartEntry> getAllDepartByClassId_edt(Connection conn,String departClassId,List<String> departIDs){
		List<DepartEntry> departEntries = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt =  conn.prepareStatement("select * from Depart,DepartClass,Depart_DepartClass where Depart.department_id = Depart_DepartClass.department_id and Depart_DepartClass.departClass_id = DepartClass.departClass_id and DepartClass.departClass_id = ?");
			stmt.setString(1, departClassId);
			rs = stmt.executeQuery();
			while(rs.next()){
				DepartEntry entry = fill(rs);
				if(departIDs.contains(entry.getDepartId())){
					departEntries.add(entry);					
				}
			}
			
			for(DepartEntry entry:departEntries){
				entry.getTasks().addAll(TaskDao.getAllTaskByDepartId_1(entry.getDepartId(), departClassId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		
		return departEntries;
	}
	
	
	public static List<DepartEntry> getAllDepartByClassId(Connection conn,String departClassId){
		List<DepartEntry> departEntries = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt =  conn.prepareStatement("select * from Depart,DepartClass,Depart_DepartClass where Depart.department_id = Depart_DepartClass.department_id and Depart_DepartClass.departClass_id = DepartClass.departClass_id and DepartClass.departClass_id = ?");
			stmt.setString(1, departClassId);
			rs = stmt.executeQuery();
			while(rs.next()){
				DepartEntry entry = fill(rs);
				departEntries.add(entry);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		
		return departEntries;
	}
	
	public static List<DepartEntry> getAllDepartByClassId_edt(Connection conn,String departClassId){
		List<DepartEntry> departEntries = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt =  conn.prepareStatement("select * from Depart,DepartClass,Depart_DepartClass where Depart.department_id = Depart_DepartClass.department_id and Depart_DepartClass.departClass_id = DepartClass.departClass_id and DepartClass.departClass_id = ?");
			stmt.setString(1, departClassId);
			rs = stmt.executeQuery();
			while(rs.next()){
				DepartEntry entry = fill(rs);
				departEntries.add(entry);
			}
			for(DepartEntry entry:departEntries){
				entry.getTasks().addAll(TaskDao.getAllTaskByDepartId_1(entry.getDepartId(), departClassId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		
		return departEntries;
	}
	
	
	public static DepartEntry fill(ResultSet rs) throws SQLException{
		DepartEntry entry = new DepartEntry();
		entry.setDepartId(rs.getString("department_id"));
		entry.setDepartName(rs.getString("department_name"));
		return entry;
	}
	
	public static boolean insert(String departName){
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " (department_name) values(?)");
			stmt.setString(1, departName);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt,conn);
		}
		return false;
	}
	
	public static long getNextId(Connection conn){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("select AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = ?");
			stmt.setString(1, TABLE_NAME);
			rs = stmt.executeQuery();
			rs.first();
			return rs.getLong(1);
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt);
		}
		return -1;
	}
	
	public static long getNextId(){
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			return getNextId(conn);
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(conn);
		}
		return -1;
	}
	

	
	public static void update(String departId,String departName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("update " + TABLE_NAME + " set department_name = ? where department_id = ?");
			stmt.setString(1, departName);
			stmt.setString(2, departId);
			stmt.execute();
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(stmt,conn);
		}
	}
	
	public static boolean delete(String departId){
		boolean result = false;
 		Connection conn = null;
 		
 		try {
			conn = DBUtils.getConnection();
			result = delete(conn,departId);
 		} catch (SQLException e) {
		}finally{
			DBUtils.close(conn);
		}
 		return result;
	}
	
	public static boolean delete(Connection conn,String departId){
		boolean result = false;
 		PreparedStatement stmt = null;
 		try {
			stmt = conn.prepareStatement("delete from " + TABLE_NAME + " where department_id = ?");
			stmt.setString(1, departId);
			result = stmt.execute();
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(stmt, conn);
		}
 		return result;
	}
	


	
	
}

package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.erp.Entry.DepartClassEntry;
import com.erp.utils.DBUtils;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;


public class DepartClassDao {
	
	private static final String TAG="DepartClassDao";
	private static final String TABLE_NAME="DepartClass";
	public static List<DepartClassEntry> getAllDepartClass(){
		return getAllDepartClass(false);
	}
	
	public static List<DepartClassEntry> getAllDepartClass(List<String> departIDs){
		List<DepartClassEntry> departClassEntries = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME );
			rs = stmt.executeQuery();
			while(rs.next()){
				departClassEntries.add(fill(rs));
			}
		
			for(DepartClassEntry entry: departClassEntries){
				entry.getDeparts().addAll(DepartDao.getAllDepartByClassId(conn, entry.getDepartClassId(),departIDs));
			}
			Iterator<DepartClassEntry> it = departClassEntries.iterator();

			while(it.hasNext()){
				if(it.next().getDeparts().size() == 0){
					it.remove();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		
		return departClassEntries;
	}
	
	
	public static List<DepartClassEntry> getAllDepartClass(boolean deep){
		List<DepartClassEntry> departClassEntries = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME );
			rs = stmt.executeQuery();
			while(rs.next()){
				departClassEntries.add(fill(rs));
			}
			if(deep){
				for(DepartClassEntry entry: departClassEntries){
					//TODO 后期要修改的话 ， 改成以 部门为主
					entry.getDeparts().addAll(DepartDao.getAllDepartByClassId(conn, entry.getDepartClassId()));
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		
		return departClassEntries;
	}
	
	
	public static DepartClassEntry fill(ResultSet rs) throws SQLException{
		DepartClassEntry entry = new DepartClassEntry();
		entry.setDepartClassName(rs.getString("departClass_name"));
		entry.setDepartClassId(rs.getString("departClass_id"));
		return entry;
	}
	
	public static boolean insert(String departClassName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " (departClass_name) values(?)");
			stmt.setString(1, departClassName);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
		return result;
		
	}
	
	public static boolean update(String departClassName,String departClassId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("update " + TABLE_NAME + " set departClass_name =? where departClass_id =?");
			stmt.setString(1, departClassName);
			stmt.setString(2, departClassId);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
		return result;
		
	}
	
	public static String getDepartClassNameByDepartId(String departId){
		String departClassName = "";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select departClass_name from DepartClass,Depart_DepartClass where DepartClass.departClass_id = Depart_DepartClass.departClass_id and Depart_DepartClass.department_id = ?''");
			stmt.setString(1, departId);
			rs = stmt.executeQuery();
			if(rs.first()){
				departClassName = rs.getString("departClass_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		return departClassName;
	}

	public static boolean delete(String deparClasstId){
		boolean result = false;
 		Connection conn = null;
 		
 		try {
			conn = DBUtils.getConnection();
			result = delete(conn,deparClasstId);
 		} catch (SQLException e) {
		}finally{
			DBUtils.close(conn);
		}
 		return result;
	}
	
	public static boolean delete(Connection conn,String deparClasstId){
		boolean result = false;
 		PreparedStatement stmt = null;
 		try {
			stmt = conn.prepareStatement("delete from " + TABLE_NAME + " where departClass_id = ?");
			stmt.setString(1, deparClasstId);
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

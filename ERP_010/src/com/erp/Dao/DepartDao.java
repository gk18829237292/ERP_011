package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Entry.DepartClassEntry;
import com.erp.Entry.DepartEntry;
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
	
	public static DepartEntry fill(ResultSet rs) throws SQLException{
		DepartEntry entry = new DepartEntry();
		entry.setDepartId(rs.getString("department_id"));
		entry.setDepartName(rs.getString("department_name"));
		return entry;
	}
	
}

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
		List<DepartEntry> departEntries = new ArrayList<>();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
//			departEntries.addAll(getAllDepartByClassId(departClassId, conn,false));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
		

		
		return departEntries;
		
	}
	
	public static void getAllDepartByClassId(Connection conn,DepartClassEntry departClassEntry,boolean deep){

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt =  conn.prepareStatement("select * from Depart,DepartClass,Depart_DepartClass where Depart.department_id = Depart_DepartClass.department_id and Depart_DepartClass.departClass_id = DepartClass.departClass_id and DepartClass.departClass_id = ?");
			stmt.setString(1, departClassEntry.getDepartClassId());
			rs = stmt.executeQuery();
			while(rs.next()){
				DepartEntry entry = fill(rs);
				entry.setDepartClass(departClassEntry);
				departClassEntry.getDeparts().add(entry);
			}
			if(deep){
				for(DepartEntry entry:departClassEntry.getDeparts()){
					TaskDao.getAllTaskByDepartId(conn, entry, deep);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		
		return;
	}
	
	public static DepartEntry fill(ResultSet rs) throws SQLException{
		DepartEntry entry = new DepartEntry();
		entry.setDepartId(rs.getString("department_id"));
		entry.setDepartName(rs.getString("department_name"));
		return entry;
	}
	
}

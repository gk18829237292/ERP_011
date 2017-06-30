package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.erp.utils.DBUtils;

import sun.security.pkcs11.Secmod.DbMode;

public class Depart_DepartClassDao {
	private static final String TAG="Depart_DepartClassDao";
	private static final String TABLE_NAME="Depart_DepartClass";
	
	public static void insert(String departId,String departClassId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn =DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?)" );
			stmt.setString(1, departId);
			stmt.setString(2, departClassId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
	}
	
	
	public static void insert(String departId,String[] departClassIds) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn =DBUtils.getConnection();
			for(String departClassId:departClassIds){
				stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?)" );
				stmt.setString(1, departId);
				stmt.setString(2, departClassId);
				stmt.execute();
				DBUtils.close(stmt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
	}
	
	
	public static void update(String departId,String departClassId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn =DBUtils.getConnection();
			stmt = conn.prepareStatement("update " + TABLE_NAME + " set departClass_id = ? where department_id= ?");
			stmt.setString(1, departClassId);
			stmt.setString(2, departId);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
	}
	
	public static void delete(String departId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn =DBUtils.getConnection();
			stmt = conn.prepareStatement("delete from " + TABLE_NAME + " where department_id= ?");
			stmt.setString(1, departId);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
	}
	
	public static List<String> getdepartClassIds(List<String> departIds) {
		Set<String> departClassIds = new HashSet<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn =DBUtils.getConnection();
			for(String departId : departIds){
				stmt = conn.prepareStatement("select departClass_id from " + TABLE_NAME + " where department_id= ?");
				stmt.setString(1, departId);
				rs = stmt.executeQuery();
				while(rs.next()){
					departClassIds.add(rs.getString("departClass_id"));
				}
				DBUtils.close(rs, stmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
		List<String> departClassIdList = new ArrayList<>();
		for(String departClassId : departClassIds){
			departClassIdList.add(departClassId);
		}
		return departClassIdList;
	}
}

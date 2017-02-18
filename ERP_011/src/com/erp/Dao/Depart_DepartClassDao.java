package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
}

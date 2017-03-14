package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Entry.DepartEntry;
import com.erp.utils.DBUtils;
import com.sun.corba.se.spi.orb.StringPair;

public class Stuff_DepartDao {
	private static final String TAG="Stuff_DepartDao";
	private static final String TABLE_NAME="Stuff_Depart";
	
	public static DepartEntry getDepartByStuffAccount(String account) {
		Connection conn = null;
		DepartEntry departEntry = null;
		try {
			conn = DBUtils.getConnection();
			departEntry = getDepartByStuffAccount(conn, account);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
		
		return departEntry;
	}
	
	public static DepartEntry getDepartByStuffAccount(Connection conn, String account) {
		DepartEntry departEntry = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt =  conn.prepareStatement("select * from Depart,Stuff_Depart where Depart.department_id = stuff_depart.department_id and Stuff_Depart.account = ?");
			stmt.setString(1, account);
			rs = stmt.executeQuery();
			if(rs.first())
				departEntry = DepartDao.fill(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt);
		}
		return departEntry;
	}
	
	public static boolean insert(String account,String departId){
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?)");
			stmt.setString(1, account);
			stmt.setString(2, departId);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
		return result;
	}
	
	public static boolean insert(String account,String[] departIds) {
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean result = true;
		try {
			conn = DBUtils.getConnection();
			for(String departId:departIds){
				stmt = conn.prepareStatement("insert into " + TABLE_NAME + " values(?,?)");
				stmt.setString(1, account);
				stmt.setString(2, departId);
				result &= stmt.execute();
				DBUtils.close(stmt);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
		return result;
	}
	
	public static boolean update(String account,String departId){
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("update " + TABLE_NAME + " set department_id =? where account =? ");
			stmt.setString(1, departId);
			stmt.setString(2, account);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
		return result;
	}
	
	public static boolean delete(String account) {
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("delete from " + TABLE_NAME + "  where account =? ");
			stmt.setString(1, account);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(stmt, conn);
		}
		return result;
	}
	
	public static List<String> getDeparts(String account) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select department_id from " + TABLE_NAME + " where account =? ");
			stmt.setString(1, account);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("department_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs,stmt, conn);
		}
		return list;
	}
	
}

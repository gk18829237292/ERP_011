package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Entry.DepartEntry;
import com.erp.Entry.StuffEntry;
import com.erp.Log.Log;
import com.erp.utils.DBUtils;
import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;
import com.sun.prism.PresentableState;

public class StuffDao {
	
	private static final String TAG="StuffDao_2";
	private static final String TABLE_NAME="Stuff_2";
	
	public static StuffEntry getStuff(Connection conn, String account,String pwd,String tableName,String type){
		StuffEntry entry = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from "+tableName+" where account = ? and pwd = ?");
			
			stmt.setString(1, account);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			
			if(rs.first()){
				entry = fill(rs);
				entry.setType(type);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt);
		}
		
		return entry;
	}
	
	public static StuffEntry getStuff(String account,String pwd){
		StuffEntry entry = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			entry = getStuff(conn, account, pwd, "stuff_0","0");
			if(entry != null) return entry;
			entry = getStuff(conn, account, pwd, "Stuff_1","1");
			if(entry != null) return entry;
			
			stmt = conn.prepareStatement("select * from Stuff_2 where account = ? and pwd = ?");
			stmt.setString(1, account);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			if(rs.first()){
				entry =  fill(rs);
				entry.setType("2");
				entry.setIsLeader(rs.getInt("type"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		
		return entry;
	}
	
	public static boolean updateStuff(String account,String pwd,String name,String telNum,String type){
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("update " + getTableName(type) + " set pwd = ? ,name=?,telNum=? where account = ?");
			stmt.setString(1, pwd);
			stmt.setString(2, name);
			stmt.setString(3, telNum);
			stmt.setString(4, account);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(stmt, conn);
		}
		return result;
	}
	
	public static StuffEntry fill(ResultSet rs) throws SQLException{
		StuffEntry entry = new StuffEntry();
		DepartEntry depart = new DepartEntry();
		entry.setAccount(rs.getString("account"));
//		entry.setPwd(rs.getString("pwd"));
		entry.setName(rs.getString("name"));
		entry.setTelNum(rs.getString("telNum"));
		return entry;
	}
	
	public static String getTableName(String type) {
		return " Stuff_" + type + " ";
	}
	
	public static List<StuffEntry> getAllStuffByType(String type){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =  null;
		List<StuffEntry> stuffEntries = new ArrayList<>();
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + getTableName(type));
			rs = stmt.executeQuery();
			while(rs.next()){
				StuffEntry entry = fill(rs);
				entry.makeItFull();
				stuffEntries.add(entry);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		return stuffEntries;
	}
	
	public static boolean deleteStuffByAccount(String account,String type){
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("delete from " + getTableName(type) + " where account = ?");
			stmt.setString(1, account);
			result = stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close( stmt, conn);
		}
		return result;
	}
	
}

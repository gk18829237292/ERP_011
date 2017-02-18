package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.erp.Entry.DepartEntry;
import com.erp.Entry.StuffEntry;
import com.erp.Log.Log;
import com.erp.utils.DBUtils;
import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

public class StuffDao {
	
	private static final String TAG="StuffDao_2";
	private static final String TABLE_NAME="Stuff_2";
	
	public static StuffEntry getStuff(Connection conn, String account,String pwd,String tableName,String type){
		StuffEntry entry = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from "+tableName+" where stuff.account = ? and stuff.pwd = ?");
			stmt.setString(1, account);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			if(rs.first())
				entry = fill(rs);
			entry.setType(type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt);
		}
		
		return null;
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
			entry = getStuff(conn, account, pwd, "stuff_1","1");
			if(entry != null) return entry;
			
			stmt = conn.prepareStatement("select * from stuff_0 where stuff.account = ? and stuff.pwd = ?");
			stmt.setString(1, account);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			if(rs.first())
				entry =  fill(rs);
			entry.setType("2");
			entry.setIsLeader(rs.getInt("type"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, stmt, conn);
		}
		
		return null;
	}
	
	public static StuffEntry fill(ResultSet rs) throws SQLException{
		StuffEntry entry = new StuffEntry();
		DepartEntry depart = new DepartEntry();
		entry.setAccount(rs.getString("account"));
		entry.setPwd(rs.getString("pwd"));
		entry.setName(rs.getString("name"));
		entry.setTelNum(rs.getString("telNum"));
		return entry;
	}
	
}

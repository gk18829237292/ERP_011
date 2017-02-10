package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.Log.Log;
import com.erp.entry.StuffEntry;
import com.erp.utils.DBUtils;

/**
 *  String account
 *  String password
 *  String name
 *  int type // 0 管理员 1 监督者 2执行者
 * @author pc_home
 *
 */
public class StuffDao {
	private static final String TAG="StuffDao";
	private static final String TABLE_NAME="Stuff";
	
	public static int insert(String account,String password,String name,String type){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into " +TABLE_NAME + " values(?,?,?,?)" );
			//设置参数
			stmt.setString(1, account);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setString(4, type);
			//执行
			stmt.execute();
			return 1;
		}catch(SQLException e){
			Log.logError(TAG, e.getMessage());
		} finally{
			DBUtils.close(stmt, conn);
		}
		return 0;
	}
	
	public static String query(String account,String password){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where account = ? and pwd = ?");
			stmt.setString(1, account);
			stmt.setString(2, password);
		 	rs =  stmt.executeQuery();
		 	String name =null;
		 	if(rs.first())
		 		name = rs.getString("name");
		 	return name;
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		return null;
	}
	
	public static StuffEntry getStuff(String account,String password){
		StuffEntry entry = new StuffEntry();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME + " where account = ? and pwd = ?");
			stmt.setString(1, account);
			stmt.setString(2, password);
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
	
	public static String getName(String account){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn =DBUtils.getConnection();
			stmt = conn.prepareStatement("select name from " + TABLE_NAME + " where account = ?");
			stmt.setString(1, account);
			rs =stmt.executeQuery();
			rs.first();
			return rs.getString("name");
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		return null;
		
	}
	
	public static List<StuffEntry> getAllStuffWithPwd(String type){
		List<StuffEntry> entrys = new ArrayList<StuffEntry>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("select * from " + TABLE_NAME +" where type = ?");
			stmt.setString(1, type);
			rs = stmt.executeQuery();
			while(rs.next()){
				StuffEntry entry = fill(rs);
				entry.setPwd(rs.getString("pwd"));
				entrys.add(entry);
			}
		} catch (SQLException e) {
			Log.logError(TAG, e.getMessage());
		}finally {
			DBUtils.close(rs, stmt, conn);
		}
		return entrys;
	}

	private static StuffEntry fill(ResultSet rs) throws SQLException{
		StuffEntry entry = new StuffEntry();
		entry.setAccount(rs.getString("account"));
		entry.setName(rs.getString("name"));
		entry.setType(rs.getInt("type"));

		
		return entry;
	}
	
	public static void updateStuff(String account,String password,String name,String type){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement("update " +TABLE_NAME + " set pwd =?,name =?,type =? where account =?" );
			//设置参数
			stmt.setString(1, password);
			stmt.setString(2, name);
			stmt.setString(3, type);
			stmt.setString(4, account);
			//执行
			stmt.execute();
			return;
		}catch(SQLException e){
			Log.logError(TAG, e.getMessage());
		} finally{
			DBUtils.close(stmt, conn);
		}
		return;
	}
}

package com.erp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
/**
 * 数据库的连接和关闭的实现
 * @author pc_home
 *
 */
public class DBUtils {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver" ;	
	private static final String URL_STRING;
	private static final String ACCOUNT_STRING;
	private static final String PWD_STRING;
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("dbconfig");
		URL_STRING = "jdbc:mysql:///erp?useUnicode=true&characterEncoding=utf8";
		ACCOUNT_STRING = "root";
		PWD_STRING = "";
	}
	
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL_STRING, ACCOUNT_STRING, PWD_STRING);
	}
	
	/**
	 * 
	 * @param rs 执行后返回的程序集
	 * @param stmt 命令集
	 * @param conn 连接对象
	 */
	public static void close(ResultSet rs,Statement stmt,Connection conn){
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){
				
			}
			rs = null;
		}
		close(stmt,conn);
	}
	
	public static void close(Statement stmt,Connection conn){
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			stmt = null;
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
			}
			conn = null;
		}
	}
	
	
	
}

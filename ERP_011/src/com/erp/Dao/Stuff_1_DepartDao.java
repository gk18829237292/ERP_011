package com.erp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erp.utils.DBUtils;

public class Stuff_1_DepartDao {
	private static final String TAG="Stuff_DepartDao";
	private static final String TABLE_NAME="Stuff_1_Depart";
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

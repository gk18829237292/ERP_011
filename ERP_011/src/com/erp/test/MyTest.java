package com.erp.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import org.junit.Test;

import com.erp.Dao.AdviceDao;
import com.erp.Dao.AdviceDao2;
import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.Stuff_DepartDao;
import com.erp.Dao.TaskDao;
import com.erp.utils.DBUtils;
import com.erp.utils.TimeUtils;

public class MyTest {
	
	@Test
	public void testQuery(){
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");

		Connection conn = null; 
		try {
			conn = DBUtils.getConnection();
			System.out.println(DepartDao.getAllDepartByClassId(conn,"1",list));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(conn);
		}
	}
}

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
import com.erp.Dao.ReportDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.Stuff_DepartDao;
import com.erp.Dao.TaskDao;
import com.erp.utils.DBUtils;
import com.erp.utils.TimeUtils;

public class MyTest {
	
	@Test
	public void testQuery(){
		
//		System.out.println(ReportDao.update("2", "1", "2", "2", "1"));
//		ReportDao.insert("1", "1", "1", "", "1");

		System.out.println(DepartDao.getAllDepart());
	}
}

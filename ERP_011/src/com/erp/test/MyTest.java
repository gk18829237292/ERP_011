package com.erp.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		

		TaskDao.update("2", "测试2017年8月30日01:29:57", 0, 0, 0, "asd", "2", "1212", "12", "ceshi", "12", "4", "", "1");
		
		
	}
}

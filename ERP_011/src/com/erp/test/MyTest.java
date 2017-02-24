package com.erp.test;

import org.junit.Test;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.TaskDao;

public class MyTest {
	
	@Test
	public void testQuery(){
		
//		DepartClassDao.update("gkTest666", "5");
		
		TaskDao.insert("测试1", 1, 1, 1, "gaoike", "1", "12", "45788", "ceshi", "2", "1","");
	}
}

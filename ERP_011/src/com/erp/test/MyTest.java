package com.erp.test;

import org.junit.Test;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.TaskDao;

public class MyTest {
	
	@Test
	public void testQuery(){
		
//		System.out.println( StuffDao.getAllStuffByType("0"));
		StuffDao.deleteStuffByAccount("gk8","2");

	}
}

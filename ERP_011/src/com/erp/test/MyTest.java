package com.erp.test;

import org.junit.Test;

import com.erp.Dao.StuffDao;

public class MyTest {
	
	@Test
	public void testQuery(){

		StuffDao.updateStuff("gk1", "456", "反馈", "123", "0");

	}
}

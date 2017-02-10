package com.erp.test;

import java.util.List;

import org.junit.Test;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.TaskDao;
import com.erp.Entry.DepartClassEntry;
import com.erp.Entry.DepartEntry;

public class MyTest {
	
	@Test
	public void testQuery(){
		TaskDao.getAllTask(20);
	}
}

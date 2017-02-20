package com.erp.test;

import org.junit.Test;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.TaskDao;

public class MyTest {
	
	@Test
	public void testQuery(){


		String departId = TaskDao.getDepartIdByTaskId("1");
		System.out.println(DepartDao.getDepartNameById(departId));
		System.out.println(DepartClassDao.getDepartClassNameByDepartId(departId));
//		System.out.println(D);
	}
}

package com.erp.test;

import java.util.List;

import org.junit.Test;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.ReportDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.TaskDao;
import com.erp.Entry.DepartClassEntry;
import com.erp.Entry.DepartEntry;
import com.erp.Entry.TaskEntry;

public class MyTest {
	
	@Test
	public void testQuery(){
		int ans = ReportDao.getReportNum("1");
		System.out.println(ans);
	}
}

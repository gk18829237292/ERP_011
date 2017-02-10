package com.erp.test;

import java.util.List;

import org.junit.Test;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.StuffDao;
import com.erp.Entry.DepartClassEntry;
import com.erp.Entry.DepartEntry;

public class MyTest {
	
	@Test
	public void testQuery(){
		List<DepartClassEntry> departClassEntries =  DepartClassDao.getAllDepartClass(true);
		System.out.println(departClassEntries);
		for(DepartClassEntry departClassEntry:departClassEntries){
//			for(DepartEntry departEntry:departClassEntries)
		}
	}
}

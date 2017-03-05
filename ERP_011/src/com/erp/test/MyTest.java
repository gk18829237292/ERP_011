package com.erp.test;

import org.junit.Test;

import com.erp.Dao.AdviceDao;
import com.erp.Dao.AdviceDao2;
import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.Stuff_DepartDao;
import com.erp.Dao.TaskDao;
import com.erp.utils.TimeUtils;

public class MyTest {
	
	@Test
	public void testQuery(){
		AdviceDao2.insert_withDelete(TimeUtils.getNowLongTime()+"", "1","", "我请你吃", "1");
	}
}

package com.erp.test;

import org.junit.Test;

import com.erp.Dao.AdviceDao;
import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.TaskDao;

public class MyTest {
	
	@Test
	public void testQuery(){
		/**
		 * goal=完成项目C, chairMan=小孙, 
		 * type=0, picture=db5b9005b6a74eb8a4c0f560b4346dc8.jpg;, reportType=0, actionType=1, name=项目C1, financing=10001, departId=1, startTime=1970-01-01 08:00, place=地点C, endTime=1970-01-01 08:00, taskid=3}
		 *
		 *(String taskId,String taskName,long startTime,long endTime,long updateEndTime,String chairMan,String type,String place,String finan,String goal,String reportType,String departId,String picture){
 		boolean result = false;
		 */
		TaskDao.update("3","测试2", 1, 1, 1, "gaoike", "1", "12", "45788", "ceshi", "2", "1","");
//		TaskDao.update("", taskName, startTime, endTime, updateEndTime, chairMan, type, place, finan, goal, reportType, departId, picture)
	}
}

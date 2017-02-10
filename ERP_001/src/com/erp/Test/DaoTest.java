package com.erp.Test;

import java.sql.Time;
import java.util.Date;

import org.junit.Test;

import com.erp.Dao.AssignmentDao;
import com.erp.Dao.Assignment_DepartmentDao;
import com.erp.Dao.DepartmentDao;
import com.erp.Dao.ReportDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.Stuff_DepartmentDao;
import com.erp.entry.ReportEntry;
import com.erp.utils.TimeUtils;

public class DaoTest {

	@Test
	public void test() {
//		System.out.println(AssignmentDao.getAllAssignmentByAccount("guest"));
		 Assignment_DepartmentDao.update("1", "2");
	}

}

package com.erp.Servlet.App;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.Depart_DepartClassDao;
import com.erp.Entry.DepartClassEntry;
import com.erp.Entry.DepartEntry;
import com.erp.utils.StringUtils;
import com.sun.java.swing.plaf.windows.resources.windows;

/**
 * Servlet implementation class DepartListServlet
 */
@WebServlet("/api/createDepart")
public class DepartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DepartListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DepartListServlet.doPost()");
		String actionType = request.getParameter("actionType");
		String departName = request.getParameter("departName");
		String[] departClassIds = request.getParameter("departClass").split(";");
		if(departClassIds != null && departClassIds.length > 0){
			switch (actionType) {
			case "0": //新增
				long nextId =  DepartDao.getNextId();
				DepartDao.insert(departName);
				Depart_DepartClassDao.insert(nextId+"", departClassIds);
				break;
			case "1": //更新
				String departId = request.getParameter("departId");
				DepartDao.update(departId, departName);
				Depart_DepartClassDao.delete(departId);
				Depart_DepartClassDao.insert(departId, departClassIds);
				break;
			}
		}
		AppUtils.sendSuccess(response);
	}

}

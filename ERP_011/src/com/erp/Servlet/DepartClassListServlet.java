package com.erp.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartClassDao;
import com.erp.Entry.DepartClassEntry;
import com.erp.utils.StringUtils;

@WebServlet("/DepartClassListServlet")
public class DepartClassListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DepartClassListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DepartClassEntry> departClassEntries = DepartClassDao.getAllDepartClass(false);
		request.setAttribute("departClassList", departClassEntries);
		request.getRequestDispatcher("/WEB-INF/jsp/departClassList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String actionType = request.getParameter("actiontype");
		String departClassName = StringUtils.change2Utf8(request.getParameter("departClassName"));
		switch (actionType) {
		case "0":
			DepartClassDao.insert(departClassName);
			break;
		case "1":
			String departClassId = request.getParameter("departClassId");
			DepartClassDao.update(departClassName, departClassId);
			break;
		}

		doGet(request, response);
	}

}

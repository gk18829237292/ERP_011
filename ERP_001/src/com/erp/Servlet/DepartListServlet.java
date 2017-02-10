package com.erp.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartmentDao;
import com.erp.Dao.StuffDao;
import com.erp.entry.DepartmentEntry;
import com.erp.entry.StuffEntry;

/**
 * Servlet implementation class DepartListServlet
 */
@WebServlet("/departList")
public class DepartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<StuffEntry> userList = StuffDao.getAllStuffWithPwd(type);
		List<DepartmentEntry> departList =  DepartmentDao.getAllDepartment();
		request.setAttribute("departList", departList);
		request.getRequestDispatcher("/WEB-INF/jsp/departList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String departName =new String( request.getParameter("departName").getBytes("ISO8859-1"),"utf-8");
		System.out.println(departName);
		int type = Integer.parseInt(request.getParameter("type"));
		System.out.println("type  " +  type);
		if(type == 1){ //insert
			DepartmentDao.insert(departName);
		}else if(type == 2){//update
			String departId = new String (request.getParameter("departId").getBytes("ISO8859-1"),"utf-8");
			System.out.println("departId" +  departId);
			DepartmentDao.update(departId, departName);
		}
		doGet(request,response);
//		request.getRequestDispatcher("/WEB-INF/jsp/departList.jsp").forward(request, response);
	}

}

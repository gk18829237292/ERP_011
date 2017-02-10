package com.erp.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.AssignmentDao;
import com.erp.Dao.Assignment_DepartmentDao;
import com.erp.Dao.DepartmentDao;
import com.erp.Log.Log;
import com.erp.entry.AssignmentEntry;
import com.erp.entry.DepartmentEntry;

/**
 * Servlet implementation class MainServler_1
 */
@WebServlet("/index1")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG="MainServler_1";
    /**
     * @see HttpServlet#HttpServlet() 
     */
    public ProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		List<AssignmentEntry> entrys =  null;
		if(request.getParameter("department") == null){
			entrys = AssignmentDao.getAllAssignment();
		}else{
			String departmentId  = request.getParameter("department");
			entrys = Assignment_DepartmentDao.getAssignmentByDepartmentId(departmentId);
		}
		
		
		request.setAttribute("projects", entrys);
		
		request.getRequestDispatcher("/WEB-INF/jsp/index1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

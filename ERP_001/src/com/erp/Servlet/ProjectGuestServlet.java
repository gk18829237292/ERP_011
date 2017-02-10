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
import com.erp.entry.AssignmentEntry;
import com.erp.entry.StuffEntry;

/**
 * Servlet implementation class ProjectGuestDetailServlet
 */
@WebServlet("/guest1")
public class ProjectGuestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectGuestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StuffEntry stuff = (StuffEntry) request.getSession().getAttribute("stuff");
		if(stuff == null){
			//TODO做转向
			System.out.println("重定向");
			response.sendRedirect("Login");
		}
		List<AssignmentEntry> entrys =null;
		
		if(request.getParameter("type") == null){
			entrys = Assignment_DepartmentDao.getAssignmentByDepartmentId(stuff.getDepartment().getDepartmentId());
		}else{
			entrys = AssignmentDao.getAllAssignmentByAccount(stuff.getAccount());
		}
		
		request.setAttribute("projects", entrys);
		request.getRequestDispatcher("/WEB-INF/jsp/guest1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

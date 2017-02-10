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
import com.erp.entry.StuffEntry;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG ="MainServlet";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Log.logInfo(TAG, "doGet");
		StuffEntry stuff = (StuffEntry) request.getSession().getAttribute("stuff");
		if(stuff == null){
			//重定向
			response.sendRedirect("Login");
		}else{
			List<DepartmentEntry> departEntrys = null;
			List<AssignmentEntry> taskList = null;
			switch (stuff.getType()) {
			case 0: //管理者
				departEntrys = DepartmentDao.getAllDepartment();
				request.setAttribute("departmentList", departEntrys);
				request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
				break;
			case 1://监督者
				departEntrys = DepartmentDao.getAllDepartment();
				taskList = AssignmentDao.getAllAssignment();
				request.setAttribute("departmentList", departEntrys);
				request.setAttribute("taskList", taskList);
				request.getRequestDispatcher("/WEB-INF/jsp/supervise.jsp").forward(request, response);
				break;
			case 2://执行者
				taskList = Assignment_DepartmentDao.getAssignmentByDepartmentId(stuff.getDepartment().getDepartmentId());
				request.setAttribute("taskList", taskList);
				request.getRequestDispatcher("/WEB-INF/jsp/guest.jsp").forward(request, response);
				break;
			default:
				//登陆错误
				response.sendRedirect("Login");
				break;
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}

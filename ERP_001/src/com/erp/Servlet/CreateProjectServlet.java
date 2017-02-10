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
import com.erp.entry.AssignmentEntry;
import com.erp.entry.DepartmentEntry;
import com.erp.entry.StuffEntry;
import com.erp.utils.TimeUtils;

/**
 * Servlet implementation class CreateProjectServlet
 */
@WebServlet("/CreateProject")
public class CreateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String assignmentId = request.getParameter("assignmentId");
		AssignmentEntry task = null;
		if(assignmentId != null){
			task = AssignmentDao.getAssignment(assignmentId);
			request.setAttribute("task", task);
			request.setAttribute("actionType", 1); // update
		}else{
			request.setAttribute("actionType", 2);	// 2 insert
		}
		List<DepartmentEntry> entrys = DepartmentDao.getAllDepartment();
		request.setAttribute("department", entrys);
		request.getRequestDispatcher("/WEB-INF/jsp/createProject.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("post in");
		//进行更新 或者添加
		//TODO 加上同步
		int type =  Integer.parseInt( request.getParameter("Actiontype"));
		String taskName = (String) request.getParameter("taskname");
		String taskPlace =(String) request.getParameter("taskPlace");
		String financing = (String) request.getParameter("financing");
		String startTime = (String) request.getParameter("startTime");
		String endTime = (String) request.getParameter("endTime");
		String num = (String) request.getParameter("num");
		String goal = (String) request.getParameter("goal");
		String departmentId = (String) request.getParameter("departmentId");
		String tasktype = (String)request.getParameter("tasktype");
		if(tasktype == null){
			tasktype = "0";
		}
		if(type == 1){ //update
			System.out.println("update ");
			String taskid = (String) request.getParameter("taskid");
			//更新任务
			AssignmentDao.update(TimeUtils.convert2Long(startTime), TimeUtils.convert2Long(endTime), taskPlace, financing, goal, tasktype, num, taskName, taskid);
			//更新任务 和 部门的关联
			 Assignment_DepartmentDao.update(taskid, departmentId);
		}else if(type ==2){//insert
			System.out.println("update ");
			String account = ((StuffEntry) request.getSession().getAttribute("stuff")).getAccount();
			long nextId = AssignmentDao.getNextId();
			AssignmentDao.insert(TimeUtils.convert2Long(startTime), TimeUtils.convert2Long(endTime), account, taskPlace, financing, goal, tasktype, num, taskName);
			Assignment_DepartmentDao.insert(nextId+"", departmentId+"");
		}
		System.out.println("over ");
		response.sendRedirect("index1");
//		request.getRequestDispatcher("index").forward(request, response);
	}
	
}

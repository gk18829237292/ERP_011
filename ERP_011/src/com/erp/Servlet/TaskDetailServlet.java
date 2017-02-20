package com.erp.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.TaskDao;

@WebServlet("/TaskDetailServlet")
public class TaskDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TaskDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskId = request.getParameter("taskId");
		String departName = request.getParameter("departName");
		String departClassName = request.getParameter("departClassName");
		
		if(departName == null){
			String departId = TaskDao.getDepartIdByTaskId(taskId);
			departName = DepartDao.getDepartNameById(departId);
			departClassName = DepartClassDao.getDepartClassNameByDepartId(departId);
		}
		request.setAttribute("departName", departName);
		if(departClassName != null){
			request.setAttribute("departClassName", "（"+departClassName +"）");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/taskDatils.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

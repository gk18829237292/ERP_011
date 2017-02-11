package com.erp.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.TaskDao;
import com.erp.Entry.StuffEntry;
import com.erp.Entry.TaskEntry;

@WebServlet("/taskServlet")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TaskServlet() {
        super();
    }

    /**
     * 最多显示20条信息
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String departId = (String) request.getParameter("departId");
		List<TaskEntry> taskEntries = null;
		if(departId == null){
			taskEntries = TaskDao.getAllTask(20);
		}else{
			taskEntries = TaskDao.getAllTaskByDepartId(departId);
		}
		request.setAttribute("taskEntries", taskEntries);
		System.out.println(taskEntries);
		request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

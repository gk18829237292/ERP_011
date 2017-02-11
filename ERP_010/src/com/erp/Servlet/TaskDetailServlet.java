package com.erp.Servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.TaskDao;
import com.erp.Entry.AdviceEntry;
import com.erp.Entry.ReportEntry;
import com.erp.Entry.TaskEntry;

@WebServlet("/taskDetailServlet")
public class TaskDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TaskDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskId = request.getParameter("taskId");
		if(taskId == null) return;
		TaskEntry taskEntry = TaskDao.getTaskById(taskId);
		Map<Integer, ReportEntry> reports = taskEntry.getReports();
		Map<Integer, AdviceEntry> advices1 = taskEntry.getAdvices1();
		Map<Integer, AdviceEntry> advices2 = taskEntry.getAdvices2();
		request.setAttribute("task", taskEntry);
		request.setAttribute("reports", reports);
		request.setAttribute("advices1", advices1);
		request.setAttribute("advices2", advices2);
		request.getRequestDispatcher("/WEB-INF/jsp/taskDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

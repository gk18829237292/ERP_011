package com.erp.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.AssignmentDao;
import com.erp.Dao.ReportDao;
import com.erp.entry.AssignmentEntry;
import com.erp.entry.ReportEntry;
import com.erp.entry.StuffEntry;

/**
 * Servlet implementation class ProjectGuestDetailServlet
 */
@WebServlet("/ProjectGuestDetail")
public class ProjectGuestDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectGuestDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("assignmentId");
		AssignmentEntry entry = null;
		List<ReportEntry> reports = null;
		StuffEntry stuff = (StuffEntry) request.getSession().getAttribute("stuff");
		if(id == null){
			//TODO 执行其他操作
		}else{
			entry = AssignmentDao.getAssignment(id);
			reports = ReportDao.getAllReport(id,stuff.getAccount());
		}
		request.setAttribute("reports", reports);
		request.setAttribute("task", entry);
		request.getRequestDispatcher("/WEB-INF/jsp/projectDetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

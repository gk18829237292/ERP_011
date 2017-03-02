package com.erp.Servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.ReportDao;
import com.erp.Entry.DepartEntry;
import com.erp.utils.ImageUtils;
import com.erp.utils.TimeUtils;

/**
 * Servlet implementation class CreateReportServlet
 */
@WebServlet("/CreateReportServlet")
public class CreateReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskId = request.getParameter("taskId");
		request.setAttribute("taskId", taskId);
		request.getRequestDispatcher("/WEB-INF/jsp/createReport.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String filePath = getServletContext().getRealPath("/img");
		String tempFilePath = getServletContext().getRealPath("/tmp");
		
		HashMap<String, String> map = ImageUtils.getMap(request, tempFilePath, filePath);
		System.out.println(map);
		//String time,String reportIndex,String comment,String picture,String task_id
		ReportDao.insert(TimeUtils.getNowLongTime()+"", map.get("reportIndex"), map.get("comment"), map.get("picture"), map.get("taskId"));
		
		DepartEntry depart = (DepartEntry) request.getSession().getAttribute("depart");
		response.sendRedirect("taskServlet?departId="+depart.getDepartId()+"&departName="+depart.getDepartName());
	}

}

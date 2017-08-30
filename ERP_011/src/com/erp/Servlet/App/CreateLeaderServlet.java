package com.erp.Servlet.App;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.AdviceDao2;
import com.erp.Dao.ReportDao;
import com.erp.Entry.DepartEntry;
import com.erp.utils.ImageUtils;
import com.erp.utils.TimeUtils;

/**
 * Servlet implementation class CreateReportServlet
 */
@WebServlet("/api/createLeader")
public class CreateLeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateLeaderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CreateLeaderServlet.doPost()");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String index = request.getParameter("index_1");
		String name = request.getParameter("name");
		String comment = request.getParameter("comment");
		String taskId = request.getParameter("taskId");

		//String time,String reportIndex,String comment,String picture,String task_id
		AdviceDao2.insert_withDelete(TimeUtils.getNowLongTime()+"",index, name,comment,taskId);
		
		AppUtils.sendSuccess(response);}

}

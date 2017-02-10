package com.erp.Servlet.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erp.Dao.AssignmentDao;
import com.erp.Dao.DepartmentDao;
import com.erp.Dao.ReportDao;
import com.erp.Log.Log;
import com.erp.entry.AssignmentEntry;
import com.erp.entry.DepartmentEntry;
import com.erp.entry.ReportEntry;
import com.erp.json.DepartJson;
import com.erp.json.ReportJson;
import com.erp.json.TaskJson;

/**
 * Servlet implementation class GetAllReportServlet
 */
@WebServlet("/api/GetAllReport")
public class GetAllReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG ="GetAllReportServlet";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		response.setCharacterEncoding("utf-8");
		
		String taskId = request.getParameter("taskId");
		int type = Integer.parseInt(request.getParameter("type"));
		
		List<ReportEntry> reports ;
		synchronized (this) {
			if(type == 2){
				String account = request.getParameter("account");
				reports=  ReportDao.getAllReport(taskId,account);
			}else{
				reports = ReportDao.getAllReport(taskId);
			}
		}
		JSONObject json = new JSONObject();
		try {
			json.put("noerror", "");
			json.put("reports", ReportJson.convert(reports));
		} catch (JSONException e) {
			try {
				json.put("error", "");
			} catch (JSONException e1) {}
		}
		
		Log.logInfo(TAG, json.toString());
		response.getWriter().append(json.toString());
		
	}
		
	

}

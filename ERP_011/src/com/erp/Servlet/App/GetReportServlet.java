package com.erp.Servlet.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erp.Dao.TaskDao;
import com.erp.Entry.AdviceEntry;
import com.erp.Entry.ReportEntry;
import com.erp.Entry.TaskEntry;
import com.erp.Entry.TaskReportEntry;


@WebServlet("/api/getReport")
public class GetReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetReportServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskId = request.getParameter("taskId");
		
		TaskEntry taskEntry = TaskDao.getTaskById(taskId);
		Map<Integer, ReportEntry> reports = taskEntry.getReports();
		Map<Integer, AdviceEntry> advices1 = taskEntry.getAdvices1();
		Map<Integer, AdviceEntry> advices2 = taskEntry.getAdvices2();
		JSONArray array = new JSONArray();
		for(int i =1;i<=taskEntry.getMaxNum();i++){
			TaskReportEntry entry =new TaskReportEntry();
			entry.setReportEntry(reports.get(i));
			entry.setSuperEntry(advices1.get(i));
			entry.setLeaderEntry(advices2.get(i));
			try {
				array.put(entry.write2Json());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		JSONObject json = new JSONObject();
		try {
			json.put("reports", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json.toString());
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}




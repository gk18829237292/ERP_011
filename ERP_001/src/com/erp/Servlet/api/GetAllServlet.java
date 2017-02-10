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
import com.erp.Log.Log;
import com.erp.entry.AssignmentEntry;
import com.erp.entry.DepartmentEntry;
import com.erp.json.DepartJson;
import com.erp.json.TaskJson;

/**
 * Servlet implementation class GetAllServlet
 */
@WebServlet("/api/GetAll")
public class GetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "GetAllServlet";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllServlet() {
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
		response.setCharacterEncoding("utf-8");
		
		int type  = Integer.parseInt(request.getParameter("type"));
		JSONObject json = new JSONObject();
		List<DepartmentEntry>  departs = DepartmentDao.getAllDepartment();
		List<AssignmentEntry> tasks =  null;
		if(type == 2){
			String account = request.getParameter("account");
			tasks =  AssignmentDao.getAllAssignmentByAccount(account);
		}else{
			tasks =  AssignmentDao.getAllAssignment();
		}
		
		try {
			JSONArray departJson = DepartJson.convert(departs);
			JSONArray taskJson = TaskJson.convert(tasks);
			json.put("departs", departJson);
			json.put("tasks", taskJson);
		} catch (JSONException e) {
			try {
				json.put("error", "");
			} catch (JSONException e1) {
			}
		}
		Log.logInfo(TAG, json.toString());
		response.getWriter().append(json.toString());
		
	}

}

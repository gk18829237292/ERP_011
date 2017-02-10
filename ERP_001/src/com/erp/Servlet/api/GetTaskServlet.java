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
import com.erp.Dao.Assignment_DepartmentDao;
import com.erp.Log.Log;
import com.erp.entry.AssignmentEntry;
import com.erp.entry.ResponseJson;
import com.erp.json.TaskJson;

/**
 * Servlet implementation class GetTaskServlet
 */
@WebServlet("/api/getTask")
public class GetTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "GetTaskServlet";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTaskServlet() {
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
		
		// 0 获取全部 1 按部门获取 2 按账号获取
		int type =0;   
		type = Integer.parseInt(request.getParameter("type"));
		
		List<AssignmentEntry> tasks =  null;
		if(type == 0){
			Log.logInfo(TAG, "ALL");
			tasks = AssignmentDao.getAllAssignment();
		}else if(type == 1){
			Log.logInfo(TAG, "depart");
			String id = request.getParameter("departId");
			tasks = Assignment_DepartmentDao.getAssignmentByDepartmentId(id);
		}else if(type == 2){
			Log.logInfo(TAG, "task");
			String account = request.getParameter("account");
			tasks = AssignmentDao.getAllAssignmentByAccount(account);
		}else{
			response.getWriter().append(ResponseJson.getErrorJson().toString());
			return;
		}
		
		JSONObject json = ResponseJson.getCurrentJson();
		try {
			JSONArray taskJson = TaskJson.convert(tasks);
			json.put("tasks",taskJson);
		} catch (JSONException e) {
			response.getWriter().append(ResponseJson.getErrorJson().toString());
			return;
		}
		Log.logInfo(TAG, json.toString());
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(json.toString());
		
	}

}

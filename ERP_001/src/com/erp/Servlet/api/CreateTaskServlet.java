package com.erp.Servlet.api;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.erp.Dao.AssignmentDao;
import com.erp.Dao.Assignment_DepartmentDao;
import com.erp.entry.StuffEntry;
import com.erp.utils.TimeUtils;

/**
 * Servlet implementation class CreateProjectServlet_1
 */
@WebServlet("/api/creatTask")
public class CreateTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTaskServlet() {
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
		request.setCharacterEncoding("utf-8");
		System.out.println("post in");
		//进行更新 或者添加
		//TODO 加上同步
		int type =  Integer.parseInt( request.getParameter("Actiontype"));
		String taskName = (String) request.getParameter("taskName");
		String taskPlace =(String) request.getParameter("taskPlace");
		String financing = (String) request.getParameter("financing");
		long startTime = Long.parseLong((String) request.getParameter("startTime"));
		long endTime =Long.parseLong( (String) request.getParameter("endTime"));
		String num = (String) request.getParameter("num");
		String goal = (String) request.getParameter("goal");
		String departmentId = (String) request.getParameter("departmentId");
		String tasktype = (String)request.getParameter("tasktype");
		String account = request.getParameter("account");
		
		for(Entry<String, String[]> entry :request.getParameterMap().entrySet()){
			System.out.println(entry.getKey() + "  " + entry.getValue()[0]);
		}
		
		if(tasktype == null){
			tasktype = "0";
		}
		if(type == 1){ //update
			System.out.println("update ");
			String taskid = (String) request.getParameter("taskid");
			//更新任务
			synchronized (this) {
				AssignmentDao.update(startTime, endTime, taskPlace, financing, goal, tasktype, num, taskName, taskid);
				//更新任务 和 部门的关联
				System.out.println("task Id : "+ taskid);
				System.out.println("departid " + departmentId);
				Assignment_DepartmentDao.update(taskid, departmentId);
			}
		}else if(type ==2){//insert
			System.out.println("insert ");
//			String account = ((StuffEntry) request.getSession().getAttribute("stuff")).getAccount();
			long nextId = AssignmentDao.getNextId();
			synchronized (this) {
				AssignmentDao.insert(startTime, endTime, account, taskPlace, financing, goal, tasktype, num, taskName);
				Assignment_DepartmentDao.insert(nextId+"", departmentId+"");
			}
			
		}
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("type", "1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("over ");
//		response.sendRedirect("index1");
		response.getWriter().append(jsonObject.toString());
//		request.getRequestDispatcher("index").forward(request, response);
	}

}

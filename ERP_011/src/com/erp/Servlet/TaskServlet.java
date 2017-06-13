package com.erp.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.Stuff_1_DepartDao;
import com.erp.Dao.Stuff_DepartDao;
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
		StuffEntry stuff = (StuffEntry) request.getSession().getAttribute("stuff");
		if(stuff == null){
			response.sendRedirect("LoginServlet");
		}
		System.out.println("stuff is : " + stuff);
		String departId = (String) request.getParameter("departId");
		String departName = (String) request.getParameter("departName");
		String departClassName = (String) request.getParameter("departClassName");
		String departClassId = request.getParameter("departClassId");
		String type = request.getParameter("type");
		System.out.println("type is :" + type);
		List<TaskEntry> taskEntries = null;
		if(type != null){
			List<String> departIDs = null;
			if(type.equals("1")){ //管理者
				departIDs =  Stuff_1_DepartDao.getDeparts(stuff.getAccount());
				System.out.println("departIds is 1 : " + departIDs);
			}else{ //领导
				departIDs =  Stuff_DepartDao.getDeparts(stuff.getAccount());
				System.out.println("departIds is 2 : " + departIDs);
			}
			System.out.println("departIds is " + departIDs);
			taskEntries = new ArrayList<>();
			for(String departIdtemp: departIDs){
				taskEntries.addAll(TaskDao.getAllTaskByDepartId(departIdtemp));
			}
		}else if(departId == null){
			taskEntries = TaskDao.getAllTask(20);
		}else if(departClassId == null){
			taskEntries = TaskDao.getAllTaskByDepartId(departId);
		}else{
			taskEntries = TaskDao.getAllTaskByDepartId_1(departId,departClassId);
		}
		List<TaskEntry> taskEntries_complete = new ArrayList<>();
		List<TaskEntry> taskEntries_not_complete = new ArrayList<>();
		
		for(TaskEntry entry:taskEntries){
			if(entry.isComplete()){
				taskEntries_complete.add(entry);
			}else {
				taskEntries_not_complete.add(entry);
			}
		}
		
		request.setAttribute("taskEntries_complete", taskEntries_complete);
		request.setAttribute("taskEntries_not_complete", taskEntries_not_complete);
		request.setAttribute("departName", departName);
		request.setAttribute("departClassName", departClassName);
		request.getRequestDispatcher("/WEB-INF/jsp/taskList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

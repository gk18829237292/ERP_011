package com.erp.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.TaskDao;
import com.erp.Entry.DepartClassEntry;
import com.erp.Entry.DepartEntry;
import com.erp.Entry.TaskEntry;
import com.erp.utils.ImageUtils;
import com.erp.utils.TimeUtils;
import com.sun.imageio.plugins.common.ImageUtil;

@WebServlet("/CreateTaskServlet")
public class CreateTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateTaskServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskId = request.getParameter("taskId");
		List<DepartClassEntry> departClassEntries =  DepartClassDao.getAllDepartClass(true);
		List<DepartEntry> departEntries = DepartDao.getAllDepart();
		if(taskId == null){
			request.setAttribute("actionType", "0"); //insert
		}else{
			TaskEntry taskEntry = TaskDao.getTaskById(taskId);
			request.setAttribute("task", taskEntry);
			request.setAttribute("actionType", "1"); //update 
		}
		request.setAttribute("departClasses", departClassEntries);
		request.setAttribute("departs", departEntries);
		request.getRequestDispatcher("/WEB-INF/jsp/createTask.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String filePath = getServletContext().getRealPath("/img");
		String tempFilePath = getServletContext().getRealPath("/tmp");
		
		HashMap<String, String> map = ImageUtils.getMap(request, tempFilePath, filePath);
		if(map.get("type") == null){
			map.put("type", "0");
		}
		System.out.println(map);
		String actionType = map.get("actionType");
		if(map.get("financing") == null || map.get("financing").length() == 0){
			map.put("financing", "0");
		}
		switch (actionType) {
		case "0": //insert
			TaskDao.insert(map.get("name"), TimeUtils.convert2Long(map.get("startTime")), TimeUtils.convert2Long(map.get("endTime")), 12, 
					map.get("chairMan"), map.get("type"), map.get("place"), map.get("financing"), map.get("goal"), map.get("reportType"), map.get("departId"), map.get("picture"),map.get("departClassId"));
			break;
		case "1":
			TaskDao.update(map.get("taskId"),map.get("name"), TimeUtils.convert2Long(map.get("startTime")), TimeUtils.convert2Long(map.get("endTime")), 12, 
					map.get("chairMan"), map.get("type"), map.get("place"), map.get("financing"), map.get("goal"), map.get("reportType"), map.get("departId"), map.get("picture"),map.get("departClassId"));
			break;
		}
		
		response.sendRedirect("taskServlet");
	}
	
	
}

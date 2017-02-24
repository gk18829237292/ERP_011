package com.erp.Servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.TaskDao;
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
		if(taskId == null){
			request.setAttribute("actionType", "1"); //update
		}else{
			TaskEntry taskEntry = TaskDao.getTaskById(taskId);
			request.setAttribute("task", taskEntry);
			request.setAttribute("actionType", "0"); //insert 
		}
		request.getRequestDispatcher("/WEB-INF/jsp/createTask.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String filePath = getServletContext().getRealPath("/img");
		String tempFilePath = getServletContext().getRealPath("/tmp");
		
		HashMap<String, String> map = ImageUtils.getMap(request, tempFilePath, filePath);
		String actionType = map.get("actionType");
		switch (actionType) {
		case "0": //insert
			TaskDao.insert(map.get("name"), TimeUtils.convert2Long(map.get("startTime")), TimeUtils.convert2Long(map.get("endTime")), 12, 
					map.get("chairMan"), map.get("type"), map.get("place"), map.get("financing"), map.get("goal"), map.get("reportType"), map.get("departId"), map.get("picture"));
			break;
		case "1":
			
			break;
		default:
			break;
		}
		
		switch (actionType) {
		case "0":
			
			break;
		case "1":
			break;
		}
	}

}

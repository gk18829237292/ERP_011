package com.erp.Servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import org.jcp.xml.dsig.internal.MacOutputStream;

import com.erp.Dao.AdviceDao;
import com.erp.Dao.AdviceDao2;
import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.TaskDao;
import com.erp.Entry.AdviceEntry;
import com.erp.Entry.ReportEntry;
import com.erp.Entry.TaskEntry;
import com.erp.utils.ImageUtils;
import com.erp.utils.StringUtils;
import com.erp.utils.TimeUtils;

@WebServlet("/TaskDetailServlet")
public class TaskDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TaskDetailServlet() {
        super();
    }

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskId = request.getParameter("taskId");
		String departName = request.getParameter("departName");
		String departClassName = request.getParameter("departClassName");
		
		sendIt(request, response, taskId, departName, departClassName);
		
	}

	/**
	 * 处理 督查以及点评信息
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String filePath = getServletContext().getRealPath("/img");
		String tempFilePath = getServletContext().getRealPath("/tmp");
		HashMap<String, String> map = ImageUtils.getMap(request, tempFilePath, filePath);
		
		String actionType = map.get("actionType");
		switch (actionType) {
		case "0": // 督查
			//String time,String adviceIndex,String comment,String picture,String taskId
			AdviceDao.insert_withDelete(TimeUtils.getNowTime(), map.get("index"), map.get("comment"), map.get("picture"), map.get("taskId"));
			break;
		case "1"://点评
			//TODO 后面
			AdviceDao2.insert_withDelete(TimeUtils.getNowTime(), map.get("index"), map.get("comment"),map.get("taskId"));
			break;
		}
		
		request.setAttribute("taskId", map.get("taskId"));
		request.setAttribute("departName", map.get("departName"));
		request.setAttribute("departClassName", map.get("departClassName"));

		sendIt(request, response, map.get("taskId"), map.get("departName"),  map.get("departClassName"));
		
	}

	  void sendIt(HttpServletRequest request, HttpServletResponse response,String taskId,String departName,String departClassName) throws ServletException, IOException{
    	if(StringUtils.isSpace(departName)){
			String departId = TaskDao.getDepartIdByTaskId(taskId);
			departName = DepartDao.getDepartNameById(departId);
			departClassName = DepartClassDao.getDepartClassNameByDepartId(departId);
		}
		request.setAttribute("departName", departName);
		if(!StringUtils.isSpace(departClassName)){
			request.setAttribute("departClassName", "（"+departClassName +"）");
		}

		TaskEntry taskEntry = TaskDao.getTaskById(taskId);
		Map<Integer, ReportEntry> reports = taskEntry.getReports();
		Map<Integer, AdviceEntry> advices1 = taskEntry.getAdvices1();
		Map<Integer, AdviceEntry> advices2 = taskEntry.getAdvices2();

		
		request.setAttribute("task", taskEntry);
		request.setAttribute("reports", reports);
		request.setAttribute("advices1", advices1);
		request.setAttribute("advices2", advices2);
		request.setAttribute("departName", departName);
		request.setAttribute("departClassName", departClassName);
		request.getRequestDispatcher("/WEB-INF/jsp/taskDatils.jsp").forward(request, response);
	  }
}

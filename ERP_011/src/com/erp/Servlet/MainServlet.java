package com.erp.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.Stuff_DepartDao;
import com.erp.Dao.TaskDao;
import com.erp.Entry.DepartClassEntry;
import com.erp.Entry.DepartEntry;
import com.erp.Entry.StuffEntry;
import com.erp.Entry.TaskEntry;
import com.erp.Log.Log;



@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MainServlet");
		StuffEntry stuff = (StuffEntry) request.getSession().getAttribute("stuff");
		
		if(stuff == null){
			response.sendRedirect("LoginServlet");
		}else{
			switch (stuff.getType()) {
			case "0": //监督者
				List<DepartClassEntry> departClassEntries = DepartClassDao.getAllDepartClass(true);
				request.setAttribute("departClassEntries", departClassEntries);
				request.getRequestDispatcher("/WEB-INF/jsp/jianduzhe.jsp").forward(request, response);
				break;
			case "1"://管理者
				departClassEntries = DepartClassDao.getAllDepartClass(true);
				request.setAttribute("departClassEntries", departClassEntries);
				request.getRequestDispatcher("/WEB-INF/jsp/guanlizhe.jsp").forward(request, response);
				break;
			case "2"://执行者
				/**
				 * 1.所在部门
				 * 2.部门的所有任务
				 * 3.
				 */
				DepartEntry depart = (DepartEntry) request.getSession().getAttribute("depart");
				List<TaskEntry> taskEntries = TaskDao.getAllTaskByDepartId(depart.getDepartId());
				request.setAttribute("taskList", taskEntries);
				if(stuff.getIsLeader() == 0){
					request.getRequestDispatcher("/WEB-INF/jsp/zhixingzhe.jsp").forward(request,response);
				}else {
					request.getRequestDispatcher("/WEB-INF/jsp/lingdao.jsp").forward(request,response);
				}
				break;
			default:
				break;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.erp.Servlet;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.TaskDao;

/**
 * Servlet implementation class QueryHighPwdServlet
 */
@WebServlet("/QueryHighPwdServlet")
public class QueryHighPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryHighPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		ResourceBundle bundle = ResourceBundle.getBundle("pwd");
		String pwd = bundle.getString("password");
		
		response.setStatus(200);
		boolean result = false;
		
		if(pwd.equals(password)){
			switch (type) {
			case 0: //删除任务
				result =  TaskDao.delete(id);
				break;
			case 1: //删除部门
				System.out.println("delete depart");
				result = DepartDao.delete(id);
				break;
			case 2:
				System.out.println("delete departclass ");
				result = DepartClassDao.delete(id);
				break;
			default:
				result = false;
				break;
			}
			
		}
		response.getWriter().write(result+"");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

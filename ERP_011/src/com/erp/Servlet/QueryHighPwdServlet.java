package com.erp.Servlet;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String taskId = request.getParameter("taskId");
		ResourceBundle bundle = ResourceBundle.getBundle("pwd");
		String pwd = bundle.getString("password");
		
		response.setStatus(200);
		boolean result = true;
		if(pwd.equals(password)){
			result =  TaskDao.delete(taskId);
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

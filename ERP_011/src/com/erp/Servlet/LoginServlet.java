package com.erp.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.StuffDao;
import com.erp.Entry.StuffEntry;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet");
		login(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet");
		//判断 如有没有传递form则重定向到login.jsp
		if(request.getParameter("username") == null || request.getParameter("password") == null ){
			login(request,response);
		}else { // 如果点击了登录，则先清空session信息
			String account = request.getParameter("username");
			String password = request.getParameter("password");
			
			request.getSession().removeAttribute("stuff");
			StuffEntry stuff = StuffDao.getStuff(account, password);
			if(stuff != null){
				request.getSession().setAttribute("stuff", stuff);
				response.sendRedirect("MainServlet");
			}else{
				login(request,response);
			}
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}
	
}

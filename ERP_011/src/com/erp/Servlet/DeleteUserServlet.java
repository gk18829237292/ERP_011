package com.erp.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.StuffDao;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String account = request.getParameter("account");
		String type = request.getParameter("type");
		StuffDao.deleteStuffByAccount(account, type);
		String type2 = request.getParameter("type2");
		System.out.println("type is " + type);
		request.setAttribute("type", type);
		if(type2.equals("0")){
			request.getRequestDispatcher("UserListServlet").forward(request, response);
		}else if(type2.equals("2")){
			request.getRequestDispatcher("UserList2Servlet").forward(request, response);
		}else if(type2.equals("3")){
			request.getRequestDispatcher("UserList3Servlet").forward(request, response);
		}
		

    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

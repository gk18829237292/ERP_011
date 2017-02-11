package com.erp.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DepartListServlet")
public class DepartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DepartListServlet() {
        super();
    }

	/**
	 * 显示部门信息
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String departClassId = request.getParameter("departClassId");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

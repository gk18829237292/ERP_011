package com.erp.Servlet.App;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.StuffDao;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/api/updateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateUserServlet.doPost()");
		String account = request.getParameter("account"); //0 update
		String password = request.getParameter("password"); //0 update
		String name = request.getParameter("name"); //0 update
		String telNum = request.getParameter("telNum"); //0 update
		String type = request.getParameter("type");
		StuffDao.updateStuff(account, password, name, telNum, type);
		AppUtils.sendSuccess(response);
	}

}

package com.erp.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.StuffDao;
import com.erp.Entry.StuffEntry;
import com.erp.utils.StringUtils;

/**
 * Servlet implementation class UpdateUserInfoServlet
 */
@WebServlet("/UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateUserInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/updateUserInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd = StringUtils.change2Utf8(request.getParameter("password"));
		String name = StringUtils.change2Utf8(request.getParameter("name"));
		String telNum = StringUtils.change2Utf8(request.getParameter("telNum"));
		
		StuffEntry stuff = (StuffEntry) request.getSession().getAttribute("stuff");
		
		StuffDao.updateStuff(stuff.getAccount(), pwd, name, telNum, stuff.getType());
		stuff.setPwd(pwd);
		stuff.setName(name);
		stuff.setTelNum(telNum);
		request.getSession().setAttribute("stuff", stuff);
		response.sendRedirect("MainServlet");

	}

}

package com.erp.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.StuffDao;
import com.erp.Entry.StuffEntry;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		List<StuffEntry> stuffEntries = StuffDao.getAllStuffByType(type);
		request.setAttribute("type", type);
		request.setAttribute("userList", stuffEntries);
		
		request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionType = request.getParameter("actionType");
		String type = request.getParameter("type");
		
		String account = request.getParameter("account");
		switch (actionType) {
		case "0": //添加
			
			break;
		case "1": //修改
			
			break;
		case "2": // 删除
			StuffDao.deleteStuffByAccount(account, type);
			break;
		}
		request.setAttribute("type", type);
		doGet(request, response);
	}

}

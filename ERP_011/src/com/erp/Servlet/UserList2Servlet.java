package com.erp.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.Stuff_DepartDao;
import com.erp.Entry.DepartEntry;
import com.erp.Entry.StuffEntry;
import com.erp.utils.StringUtils;
import com.sun.glass.ui.CommonDialogs.Type;

@WebServlet("/UserList2Servlet")
public class UserList2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String type ="2";
    public UserList2Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DepartEntry> departEntries = DepartDao.getAllDepart();
		List<StuffEntry> stuffEntries = StuffDao.getAllStuffByType_Depart(type);
		request.setAttribute("type", type);
		request.setAttribute("userList", stuffEntries);
		request.setAttribute("departs", departEntries);
		request.getRequestDispatcher("/WEB-INF/jsp/userList2.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionType = request.getParameter("actiontype");
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = StringUtils.change2Utf8(request.getParameter("name"));
		String telNum = request.getParameter("telNum");
		String departId = request.getParameter("departId");
		switch (actionType) {
		case "0": //添加
			StuffDao.insert(account, password, name, telNum, type);
			Stuff_DepartDao.insert(account, departId);
			break;
		case "1": //修改
			StuffDao.update(account, password, name, telNum, type);
			System.out.println("account");
			if(!Stuff_DepartDao.update(account, departId)){
				Stuff_DepartDao.insert(account, departId);
			}
			break;

		}
		request.setAttribute("type", type);
		doGet(request, response);
	}

}
